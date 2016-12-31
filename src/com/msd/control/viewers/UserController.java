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

	// This view will display the correctness of the user credentials
	@RequestMapping(value = "/log", method = RequestMethod.POST, params = "login")
	public String logUserIn(@ModelAttribute("info") LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndThat(info)) {
			Applicant user = poolApplicants.fetchApplicant(info.getUsername());
			model.addAttribute("user", user);
			List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
			model.addAttribute("vacancies", vacancies);
		} else {
			redirects.addFlashAttribute("error", "Username or Password is wrong!");
			return "redirect:/user_login";
		}
		return "displays/show_user";
	}

	// Display User details
	@RequestMapping(value = "/{indexNumber}", method = RequestMethod.GET)
	public String showCompany(@PathVariable("indexNumber") String indexNumber, Model model) {
		// Fetch applicant from database
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
		model.addAttribute("vacancies", vacancies);
		return "displays/show_user";
	}

	// This view will direct to the register view
	@RequestMapping(value = "/log", method = RequestMethod.POST, params = "register")
	public String register(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		poolPW.addPassword(info);
		return "redirect:/reg/user/";
	}

	// This will display all the users and their passwords <REMOVE>
	@RequestMapping("/user_list")
	public ModelAndView loadUsersList() {
		List<LoginInfo> list = poolPW.listTypeOfPWs(false);
		return new ModelAndView("displays/user_list", "list", list);
	}

	// This will delete the current password
	@RequestMapping(value = "/deletepw/{name}", method = RequestMethod.GET)
	public String delete(@PathVariable String name) {
		poolPW.deletePassword(name);
		return ("redirect:/user/user_list");
	}

	@RequestMapping(value = "/apply/{vacancyID}/{indexNumber}", method = RequestMethod.GET)
	public String applyForVacancy(@PathVariable("vacancyID") int vacancyID,
			@PathVariable("indexNumber") String indexNumber, final RedirectAttributes redirectAttributes) {
		poolVacancies.closeVacancy(vacancyID, indexNumber);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Application submitted succesfully!");
		return "redirect:/user/" + indexNumber;
	}
}
