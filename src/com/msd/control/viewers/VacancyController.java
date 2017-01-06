package com.msd.control.viewers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Vacancy;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolApplicants;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolVacancies;
import com.msd.pool.validators.PoolVacancyValidator;

@Controller
@RequestMapping("vacancy")
public class VacancyController implements Preferences {

	@Autowired
	PoolVacancyValidator vacancyValidator;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolVacancies poolVacancies;
	@Autowired
	PoolCompanies poolCompanies;

	/*****************************************************************************************
	 * This method is the validator binder. It will bind the vacancy details
	 * validator to the vacancy creation page when a update or a new vacancy is
	 * happening
	 ****************************************************************************************/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(vacancyValidator);
	}

	/*****************************************************************************************
	 * This method will be called when a new vacancy is being created. It will
	 * generate details required by a vacancy and pass them to the vacancy
	 * creation form.
	 ****************************************************************************************/
	@RequestMapping(value = "/add/{company}", method = RequestMethod.POST)
	public String newVacancy(Model model, @PathVariable("company") String companyName) {
		// Pass the Vacancy as "vacancyForm"
		model = generatePrefList(model);
		// Create a vacancy with a company attached to it with status open
		model.addAttribute("vacancyForm", new Vacancy(companyName, null, true));
		model.addAttribute("company", poolCompanies.getCompanyName(companyName));
		// Notify that this is a new vacancy
		model.addAttribute("status", true);
		// Display the html page
		return "logins/new_vacancy";
	}

	/*****************************************************************************************
	 * This method will be called when a company wants to delete a vacancy they
	 * have created. When deleting a vacancy, 1. Vacancy should be deleted from
	 * vacancy table 2. Decrement the # of positions in the company table 3.
	 * Delete choice from the applicant table
	 ****************************************************************************************/
	@RequestMapping(value = "/delete/{vacancyID}", method = RequestMethod.POST)
	public String deletesVacancy(@PathVariable("vacancyID") int vacancyID, RedirectAttributes redirectAttributes) {
		// Get the company name of the vacancy
		String companyName = poolVacancies.getCompanyName(vacancyID);
		// Delete vacancy
		poolVacancies.deleteVacancy(vacancyID);
		poolCompanies.decrementVacancyCount(companyName);
		// TODO : Delete user relationships when deleting a vacancy
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Vacancy deleted!");
		redirectAttributes.addFlashAttribute("css", "success");
		return "redirect:/company/view/" + companyName;
	}

	/*****************************************************************************************
	 * This method will be called when a company wants to update a vacancy they
	 * have created.
	 ****************************************************************************************/
	@RequestMapping(value = "/update/{vacancyID}", method = RequestMethod.POST)
	private String updatesVacancy(@PathVariable("vacancyID") int vacancyID, Model model) {
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
	 * This method will be called when a company has created a new vacancy. It
	 * will be validated and added to the vacancy table.
	 ****************************************************************************************/
	@RequestMapping(value = "vacancies", method = RequestMethod.POST, params = "create")
	public String addVacancy(@ModelAttribute("vacancyForm") @Validated Vacancy vacancy, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model = generatePrefList(model);
			return "logins/new_vacancy";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("msg", "Vacancy created successfully!");
			redirectAttributes.addFlashAttribute("css", "success");
			// Add Vacancy to the Vacancy table
			poolVacancies.addVacancy(vacancy);
			poolCompanies.incrementVacancyCount(vacancy.getCompanyID());
			// Display Vacancy details
			return "redirect:/company/view/" + vacancy.getCompanyID();
		}
	}

	/*****************************************************************************************
	 * This method will be called when a company is updating a vacancy they have created. It
	 * will be validated and updated.
	 ****************************************************************************************/
	@RequestMapping(value = "vacancies", method = RequestMethod.POST, params = "update")
	public String updateVacancy(@ModelAttribute("vacancyForm") @Validated Vacancy vacancy, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model = generatePrefList(model);
			return "logins/new_vacancy";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("msg", "Vacancy updated successfully!");
			redirectAttributes.addFlashAttribute("css", "success");
			// Add Vacancy to the Vacancy table
			poolVacancies.updateVacancy(vacancy);
			// Display Vacancy details
			return "redirect:/company/view/" + vacancy.getCompanyID();
		}
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