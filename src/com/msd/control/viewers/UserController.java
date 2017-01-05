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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Applicant;
import com.msd.items.Company;
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

	// This view will display the correctness of the user credentials
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logUserIn(@ModelAttribute("info") LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndThat(info)) {
			Applicant user = poolApplicants.fetchApplicant(info.getUsername());
			model.addAttribute("user", user);
			List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
			for (Vacancy vacancy : vacancies) {
				vacancy.setCompany(poolCompanies.getCompanyName(vacancy.getCompany()));
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
			vacancy.setCompany(poolCompanies.getCompanyName(vacancy.getCompany()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}

	@RequestMapping(value = "/apply/{vacancyID}/{indexNumber}", method = RequestMethod.POST)
	public String applyForVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber) {
		poolVacancies.closeVacancy(vacancyID, indexNumber);
		// Pass the successful message to redirect
		model.addAttribute("msg", "Application submitted succesfully!");
		model.addAttribute("css", "success");
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		for (Vacancy vacancy : vacancies) {
			vacancy.setCompany(poolCompanies.getCompanyName(vacancy.getCompany()));
		}
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}
}
