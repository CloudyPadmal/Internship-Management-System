package com.msd.control.viewers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Applicant;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.items.PoolApplicants;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolVacancies poolVacancies;
	@Autowired
	PoolCompanies poolCompanies;

	/*****************************************************************************************
	 * This method will be called when a user clicks LOG IN with user login ID
	 * and password. The passed down values (user name, password) then will be
	 * checked for validity with poolPW. If the credentials are invalid, view
	 * will be redirected to the login page with a warning message. If they are
	 * valid, it will be redirected to the user home page.
	 ****************************************************************************************/
	// This view will display the correctness of the user credentials
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logUserIn(@ModelAttribute("info") LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndThat(info)) {
			Applicant user = poolApplicants.fetchApplicant(info.getUsername());
			model.addAttribute("user", user);
			List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
			for (Vacancy vacancy : vacancies) {
				vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
			}
			model.addAttribute("vacancies", vacancies);
		} else {
			redirects.addFlashAttribute("msg", "Username or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/user_login";
		}
		return "displays/show_user";
	}

	// Display User details
	@RequestMapping(value = "/{indexNumber}", method = RequestMethod.POST)
	public String showCompany(@PathVariable("indexNumber") String indexNumber, Model model) {
		// Fetch applicant from database
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		for (Vacancy vacancy : vacancies) {
			vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to apply for a vacancy by a
	 * company. When applying for a vacancy,
	 * 1. Vacancy should be closed and the applicant index number should be added
	 * 2. Applicant table should be updated with the appropriate choice
	 ****************************************************************************************/
	@RequestMapping(value = "/apply/{vacancyID}/{indexNumber}/{choice}", method = RequestMethod.POST)
	public String applyForVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("choice") int choice) {
		// Update the vacancy table by closing the vacancy with applicant name
		poolVacancies.closeVacancy(vacancyID, indexNumber);
		// Update the user table giving the choice to the applicant
		poolApplicants.setChoice(indexNumber, choice, vacancyID);
		// Pass the successful message to redirect
		model.addAttribute("msg", "Application submitted succesfully!");
		model.addAttribute("css", "success");
		// Fetch the applicant
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		// Fetch the list of vacancies
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		for (Vacancy vacancy : vacancies) {
			vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to delete a vacancy they selected.
	 * When deleting a vacancy,
	 * 1. Vacancy should be stated open
	 * 2. Delete vacancy choice from user table
	 ****************************************************************************************/
	@RequestMapping(value = "/cancel/{vacancyID}/{indexNumber}/{choice}", method = RequestMethod.POST)
	public String cancelVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("choice") int choice) {
		// Update the vacancy table by closing the vacancy with applicant name
		poolVacancies.openVacancy(vacancyID);
		// Update the user table giving the choice to the applicant
		poolApplicants.removeChoice(indexNumber, choice, vacancyID);
		// Pass the successful message to redirect
		model.addAttribute("msg", "Application rejected succesfully!");
		model.addAttribute("css", "warning");
		// Fetch the applicant
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		// Fetch the list of vacancies
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		for (Vacancy vacancy : vacancies) {
			vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to request a vacancy someone else
	 * have accepted. It will be sent as a request to the administrator
	 ****************************************************************************************/
	@RequestMapping(value = "/request/{vacancyID}/{indexNumber}", method = RequestMethod.POST)
	public String requestVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber) {
		// Pass the successful message to redirect
		model.addAttribute("msg", "Request sent to administrator!");
		model.addAttribute("css", "info");
		// Fetch the applicant
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		// Fetch the list of vacancies
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		for (Vacancy vacancy : vacancies) {
			vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}
}
