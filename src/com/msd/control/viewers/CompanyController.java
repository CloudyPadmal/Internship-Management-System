package com.msd.control.viewers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Company;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("company")
public class CompanyController implements Preferences {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolVacancies poolVacancies;

	/*****************************************************************************************
	 * This method will be called when a company clicks LOG IN with company
	 * login ID and password. The passed down values (user name, password) then
	 * will be checked for validity with poolPW. If the credentials are invalid,
	 * view will be redirected to the login page with a warning message. If they
	 * are valid, it will be redirected to the company home page.
	 ****************************************************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "login")
	private String companyLogin(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndThat(info)) {
			// Password and the credentials are matching. Load company details
			Company company = poolCompanies.fetchCompany(info.getUsername());
			if (company == null) {
				// Password and credentials don't match. Redirect to login page.
				redirects.addFlashAttribute("msg", "Company not found!");
				redirects.addFlashAttribute("css", "warning");
				// This will not happen. But has handled it anyways
				return "redirect:/company_login";
			}
			// Load a list of vacancies posted by the company
			List<Vacancy> listOfVacancies = poolVacancies.getCompanyVacancies(company.getLoginID());
			for (Vacancy vacancy : listOfVacancies) {
				vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
			}
			// Add the company under "company"
			model.addAttribute("company", company);
			model.addAttribute("vacancies", listOfVacancies);
			return "displays/show_company";
		} else {
			// Password and credentials don't match. Redirect to login page.
			redirects.addFlashAttribute("msg", "Company or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/company_login";
		}
	}

	/*****************************************************************************************
	 * This method will be called when a company wants to delete a vacancy they
	 * have created. When deleting a vacancy,
	 * 1. Vacancy should be deleted from vacancy table
	 * 2. Decrement the # of positions in the company table
	 * 3. Delete choice from the applicant table
	 ****************************************************************************************/
	@RequestMapping(value = "/vacancy/{vacancyID}/delete", method = RequestMethod.POST)
	private String deleteVacancy(@PathVariable("vacancyID") int vacancyID,
			final RedirectAttributes redirectAttributes) {
		String companyName = poolVacancies.getCompanyName(vacancyID);
		// Delete vacancy
		poolVacancies.deleteVacancy(vacancyID);
		poolCompanies.decrementVacancyCount(companyName);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Vacancy deleted!");
		redirectAttributes.addFlashAttribute("css", "success");
		return "redirect:/company/" + companyName;
	}

	// Update Vacancy
	@RequestMapping(value = "/vacancy/{vacancyID}/update", method = RequestMethod.POST)
	private String showUpdateVacancy(@PathVariable("vacancyID") int vacancyID, Model model) {
		// Fetch the Vacancy details from database
		Vacancy vacancy = poolVacancies.fetchVacancy(vacancyID);
		// Add details under "vacancyForm"
		model.addAttribute("vacancyForm", vacancy);
		model.addAttribute("company", poolCompanies.getCompanyName(vacancy.getCompanyID()));
		// Generate preference list
		model = generatePrefList(model);
		// Add update notations
		model.addAttribute("status", false);
		return "logins/new_vacancy";
	}

	/*****************************************************************************************
	 * This method will generate the preferences in to a list which will be used
	 * in generating the web form. It will be passed down to the page with the
	 * key "preferences"
	 ****************************************************************************************/
	private Model generatePrefList(Model model) {
		// Create a list of preferences
		List<String> preferences = new ArrayList<>();
		preferences.add(ANTENNAS);
		preferences.add(BIOMECHANICS);
		preferences.add(BIOMEDICAL);
		preferences.add(WIFI);
		preferences.add(ARDUINO);
		preferences.add(AUTOMATION);
		preferences.add(AI);
		preferences.add(CIRCUITS);
		preferences.add(FPGA);
		preferences.add(IMAGEPROCESSING);
		preferences.add(IOT);
		preferences.add(NETWORKING);
		preferences.add(PROCESSORDESIGN);
		preferences.add(PROGRAMMING);
		preferences.add(SEMICONDUCTORS);
		preferences.add(SIGNALPROCESSING);
		preferences.add(TELECOM);
		// Add them under "preferences"
		model.addAttribute("preferences", preferences);
		return model;
	}
}