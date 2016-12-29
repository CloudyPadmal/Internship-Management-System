package com.msd.control;

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

import com.msd.pool.PoolApplicants;
import com.msd.pool.PoolVacancies;
import com.msd.pool.validators.PoolVacancyValidator;
import com.msd.poolinterfaces.Preferences;
import com.msd.users.Vacancy;

@Controller
@RequestMapping("vacancy")
public class VacancyController implements Preferences {

	@Autowired
	PoolVacancyValidator vacancyValidator;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolVacancies poolVacancies;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(vacancyValidator);
	}

	// Creation form for a new vacancy
	@RequestMapping(value = "/{company}", method = RequestMethod.GET)
	public String registerVacancy(Model model, @PathVariable("company") String companyName) {
		// Pass the Vacancy as "vacancyForm"
		model = generatePrefList(model);
		model.addAttribute("vacancyForm", new Vacancy(companyName));
		// Display the html page
		return "logins/new_vacancy";
	}

	// All vacancies in the table
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String showAllVacancies(Model model) {
		// Add the vacancies list under "vacancies"
		model.addAttribute("vacancies", poolVacancies.getAllVacancies());
		return "displays/vacancy_list";
	}

	// Delete Vacancy
	@RequestMapping(value = "/{index}/delete", method = RequestMethod.GET)
	public String deleteVacancy(@PathVariable("index") String vacancy, final RedirectAttributes redirectAttributes) {
		poolVacancies.deleteVacancy(vacancy);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Vacancy deleted!");
		return "redirect:/";
	}

	// Display Vacancy details
	@RequestMapping(value = "/show/{index}", method = RequestMethod.GET)
	public String showVacancy(@PathVariable("index") String name, Model model) {
		// Fetch Vacancy from database
		Vacancy vacancy = poolVacancies.fetchVacancy(name);
		if (vacancy == null) {
			// If there is no Vacancy, return a failure message
			model.addAttribute("msg", "Vacancy not found");
		}
		// Add the Vacancy under "vacancy"
		model.addAttribute("vacancy", vacancy);
		return "displays/show_vacancy";
	}

	// This will be called upon clicking register button
	@RequestMapping(value = "vacancies", method = RequestMethod.POST)
	public String addVacancy(@ModelAttribute("vacancyForm") @Validated Vacancy vacancy, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model = generatePrefList(model);
			return "logins/new_vacancy";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("msg", "Vacancy created successfully!");
			// Add Vacancy to the Vacancy table
			poolVacancies.addVacancy(vacancy);
			// Display Vacancy details
			return "redirect:/" + vacancy.getId();
		}
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
