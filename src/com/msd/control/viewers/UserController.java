package com.msd.control.viewers;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

	/*****************************************************************************************
	 * This method will be the redirected method by POST methods related to
	 * applicant. This will show the user page with user details and vacancies
	 * @throws ServletException 
	 ****************************************************************************************/
	@RequestMapping(value = "/view/{indexNumber}", method = RequestMethod.GET)
	public String userHomePage(Model model, @PathVariable("indexNumber") String indexNumber,
			RedirectAttributes redirects) throws ServletException {
		// Fetch the applicant
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !auth.getName().equals(indexNumber)){
			throw new ServletException("Unauthorised");
		}
		System.out.println(auth);
		// {indexNumber} is not required, it can be fetch from auth.getName() which is the username. That can be used to fetc data for logged in user
		/*try {
			if (model.asMap().get("user").equals(indexNumber)) {
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
			} else {
				return "redirect:/";
			}
		} catch (NullPointerException e) {
			return "redirect:/";
		}*/
		
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
		redirects.addFlashAttribute("user", indexNumber);
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
		redirects.addFlashAttribute("user", indexNumber);
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
		redirects.addFlashAttribute("user", indexNumber);
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
		redirects.addFlashAttribute("user", indexNumber);
		return "redirect:/user/view/" + indexNumber;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logOut(Model model, HttpServletRequest request, RedirectAttributes redirects) {
		// Generate the request 
		// Inactive means deactivate user completely, not during the session
		//poolPW.makeInactive(indexNumber);
		// Pass the successful message to redirect
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, null, auth);
	    }
		
		redirects.addFlashAttribute("msg", "Logged out!");
		redirects.addFlashAttribute("css", "success");
		return "redirect:/";
	}
}
