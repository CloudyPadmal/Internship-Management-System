package com.msd.control.viewers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Applicant;
import com.msd.items.Company;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;
import com.msd.pool.validators.PoolCompanyValidator;

@Controller
@RequestMapping("company")
public class CompanyController implements Preferences {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolVacancies poolVacancies;

	// This view will display the correctness of the user credentials
	@RequestMapping(value = "/log", method = RequestMethod.POST, params = "login")
	public String logUserIn(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		System.out.println(info.getPassword() + " " + info.getUsername() + " " + info.getencodedPassword());
		if (poolPW.matchThisAndThat(info)) {
			return "redirect:" + info.getUsername();
		} else {
			redirects.addFlashAttribute("error", "Company or Password is wrong!");
			return "redirect:/company_login";
		}
	}

	// This view will direct to the register view
	@RequestMapping(value = "/log", method = RequestMethod.POST, params = "register")
	public String register(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		return "redirect:/reg/company/";
	}

	// This will display the existing company list
	@RequestMapping("/company_list")
	public ModelAndView loadCompanyList() {
		List<LoginInfo> list = poolPW.listTypeOfPWs(true);
		return new ModelAndView("displays/user_list", "list", list);
	}

	// This will delete the current password
	@RequestMapping(value = "/deletepw/{name}", method = RequestMethod.GET)
	public String delete(@PathVariable String name) {
		poolPW.deletePassword(name);
		return ("redirect:/company/company_list");
	}

	// Display Company details
	@RequestMapping(value = "/{index}", method = RequestMethod.GET)
	public String showCompany(@PathVariable("index") String companyName, Model model) {
		// Fetch applicant from database
		Company company = poolCompanies.fetchCompany(companyName);
		List<Vacancy> listOfVacancies = poolVacancies.getCompanyVacancies(companyName);
		if (company == null) {
			// If there is no user, return a failure message
			model.addAttribute("msg", "Company not found");
		}
		// Add the company under "company"
		model.addAttribute("company", company);
		model.addAttribute("vacancies", listOfVacancies);
		return "displays/show_company";
	}

	// Delete Vacancy
	@RequestMapping(value = "/vacancy/{vacancyID}/delete", method = RequestMethod.GET)
	public String deleteVacancy(@PathVariable("vacancyID") int vacancyID, final RedirectAttributes redirectAttributes) {
		String companyName = poolVacancies.getCompanyName(vacancyID);
		// Delete vacancy
		poolVacancies.deleteVacancy(vacancyID);
		poolCompanies.decrementVacancyCount(companyName);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Vacancy deleted!");
		return "redirect:/company/" + companyName;
	}

	// Delete Vacancy
	@RequestMapping(value = "/vacancy/{vacancyID}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("vacancyID") int vacancyID, Model model) {
		// Fetch the Vacancy details from database
		Vacancy vacancy = poolVacancies.fetchVacancy(vacancyID);
		// Add details under "userForm"
		model.addAttribute("vacancyForm", vacancy);
		// Generate preference list
		model = generatePrefList(model);
		// Add update notations
		model.addAttribute("status", false);
		return "logins/new_vacancy";
	}

	// Generate default values for preferences
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