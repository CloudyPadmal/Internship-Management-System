package com.msd.control.viewers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Appeal;
import com.msd.items.Applicant;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.items.PoolApplicants;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolRequests;
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
	@Autowired
	PoolRequests poolRequests;
	private @Autowired AuthenticationManager authenticationManager;

	/*****************************************************************************************
	 * This method will be called when a user clicks LOG IN with user login ID
	 * and password. The passed down values (user name, password) then will be
	 * checked for validity with poolPW. If the credentials are invalid, view
	 * will be redirected to the login page with a warning message. If they are
	 * valid, it will be redirected to the user home page.
	 ****************************************************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logUserIn(@ModelAttribute("info") LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		try {
			poolPW.makeActive(info.getUsername());
			Authentication request = new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			redirects.addFlashAttribute("msg", "Logged in successfully!");
			redirects.addFlashAttribute("css", "info");
			return "redirect:/user/view/" + info.getUsername();
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
			redirects.addFlashAttribute("msg", "Username or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/user_login";
		}
	}

	/*****************************************************************************************
	 * This method will be the redirected method by POST methods related to
	 * applicant. This will show the user page with user details and vacancies
	 ****************************************************************************************/
	@RequestMapping(value = "/view/{indexNumber}", method = RequestMethod.GET)
	public String userHomePage(Model model, @PathVariable("indexNumber") String indexNumber,
			RedirectAttributes redirects) {
		// Fetch the applicant
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		// {indexNumber} is not required, it can be fetch from auth.getName() which is the username. That can be used to fetc data for logged in user
		Applicant user = poolApplicants.fetchApplicant(indexNumber);
		model.addAttribute("user", user);
		// If the user got a chance already, show the congratulation message
		if (user.isAwarded()) {
			Vacancy award = poolVacancies.fetchVacancy(user.getAwardedVacancy());
			award.setCompanyName(poolCompanies.getCompanyName(award.getCompanyID()));
			model.addAttribute("congrats", award);
		} else {
			// Fetch the list of vacancies
			List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
			for (Vacancy vacancy : vacancies) {
				vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
			}
			model.addAttribute("vacancies", vacancies);
		}
		return "displays/show_user";
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to apply for a vacancy by a
	 * company. When applying for a vacancy, 1. Vacancy should be closed and the
	 * applicant index number should be added 2. Applicant table should be
	 * updated with the appropriate choice
	 ****************************************************************************************/
	@RequestMapping(value = "/apply/{vacancyID}/{indexNumber}/{choice}", method = RequestMethod.POST)
	public String applyForVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("choice") int choice,
			RedirectAttributes redirects) {
		// Update the vacancy table by closing the vacancy with applicant name
		poolVacancies.closeVacancy(vacancyID, indexNumber, choice);
		// Update the user table giving the choice to the applicant
		poolApplicants.setChoice(indexNumber, choice, vacancyID);
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Application submitted succesfully!");
		redirects.addFlashAttribute("css", "success");
		return "redirect:/user/view/" + indexNumber;
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to delete a vacancy they
	 * selected. When deleting a vacancy, 1. Vacancy should be stated open 2.
	 * Delete vacancy choice from user table
	 ****************************************************************************************/
	@RequestMapping(value = "/cancel/{vacancyID}/{indexNumber}/{choice}", method = RequestMethod.POST)
	public String cancelVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("choice") int choice,
			RedirectAttributes redirects) {
		// Update the vacancy table by closing the vacancy with applicant name
		poolVacancies.openVacancy(vacancyID);
		// Update the user table giving the choice to the applicant
		poolApplicants.removeChoice(indexNumber, choice);
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Application Cancelled!");
		redirects.addFlashAttribute("css", "warning");
		return "redirect:/user/view/" + indexNumber;
	}

	/*****************************************************************************************
	 * This method will be called when a user wants to request a vacancy someone
	 * else have accepted. It will be sent as a request to the administrator
	 ****************************************************************************************/
	@RequestMapping(value = "/request/{vacancyID}/{indexNumber}", method = RequestMethod.POST)
	public String requestVacancy(@PathVariable("vacancyID") int vacancyID, Model model,
			@PathVariable("indexNumber") String indexNumber, RedirectAttributes redirects) {
		// Generate the request
		Appeal appeal = new Appeal(vacancyID, indexNumber);
		// Add the request to the request table
		appeal.setGradedPoint(poolApplicants.getGPA(indexNumber));
		appeal.setVacancyName(poolVacancies.getVacancyName(vacancyID));
		appeal.setCurrentNumber(poolVacancies.getApplicant(vacancyID));
		appeal.setCurrentGradedPoint(poolApplicants.getGPA(appeal.getCurrentNumber()));
		poolRequests.addRequest(appeal);
		poolApplicants.addRequest(indexNumber, vacancyID);
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Request sent to administrator!");
		redirects.addFlashAttribute("css", "info");
		return "redirect:/user/view/" + indexNumber;
	}

	@RequestMapping(value = "/request/cancel/{indexNumber}", method = RequestMethod.POST)
	public String cancelRequestedVacancy(Model model, @PathVariable("indexNumber") String indexNumber,
			RedirectAttributes redirects) {
		// Delete the request
		poolApplicants.deleteRequest(indexNumber);
		poolRequests.deleteRequestsByUser(indexNumber);
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Request cancelled!");
		redirects.addFlashAttribute("css", "info");
		return "redirect:/user/view/" + indexNumber;
	}

	@RequestMapping(value = "/logout/{indexNumber}", method = RequestMethod.POST)
	public String logOut(Model model, @PathVariable("indexNumber") String indexNumber, RedirectAttributes redirects) {
		// Generate the request
		poolPW.makeInactive(indexNumber);
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Logged out!");
		redirects.addFlashAttribute("css", "success");
		return "redirect:/";
	}
}
