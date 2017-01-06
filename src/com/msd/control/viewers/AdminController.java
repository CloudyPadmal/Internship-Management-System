package com.msd.control.viewers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.msd.pool.items.PoolRequests;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolVacancies poolVacancies;
	@Autowired
	PoolRequests poolRequests;

	/*****************************************************************************************
	 * This method will generate the view for an administrative login
	 ****************************************************************************************/
	@RequestMapping("/")
	public ModelAndView loginAdmin() {
		ModelAndView loginModel = new ModelAndView();
		LoginInfo info = new LoginInfo();
		info.setCompany(true);
		loginModel.setViewName("logins/main_login");
		loginModel.addObject("info", info);
		loginModel.addObject("action_url", "login");
		loginModel.addObject("principal", "Admin");
		loginModel.addObject("admin", true);
		return loginModel;
	}

	/*****************************************************************************************
	 * When the Administrator tries to log in, credentials will be checked and
	 * if they are matching, he will be redirected to a view containing users,
	 * companies, and vacancies lists
	 ****************************************************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String adminLogin(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndAdmin(info)) {
			return "logins/admin_home";
		} else {
			redirects.addFlashAttribute("msg", "Login Failed!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/admin/";
		}
	}

	/*****************************************************************************************
	 * This method will generate a list of all the users and their details and
	 * passed down to display the complete list of them.
	 ****************************************************************************************/
	@RequestMapping("/view_users")
	public String showAllUsers(Model model) {
		// Add the user list under "users"
		model.addAttribute("users", poolApplicants.getAllApplicants());
		return "displays/full_user_list";
	}

	/*****************************************************************************************
	 * This method will generate a list of all the companies and their details
	 * and passed down to display the complete list of them.
	 ****************************************************************************************/
	@RequestMapping("/view_companies")
	public String showAllCompanies(Model model) {
		// Add the user list under "users"
		model.addAttribute("companies", poolCompanies.getAllCompanies());
		return "displays/full_company_list";
	}

	/*****************************************************************************************
	 * This method will generate a list of all the vacancies post by all the
	 * companies and their details and passed down to display the complete list
	 * of them.
	 ****************************************************************************************/
	@RequestMapping("/view_vacancies")
	public String showAllVacancies(Model model) {
		// Add the user list under "users"
		model.addAttribute("vacancies", poolVacancies.getAllVacancies());
		return "displays/full_vacancy_list";
	}

	/*****************************************************************************************
	 * This method will generate a list of all the requests by applicants who
	 * haven't got to select the vacancies they like. Administrator will look
	 * for ward to their requests and either accept or decline them.
	 ****************************************************************************************/
	@RequestMapping("/view_requests")
	public String showAllRequests(Model model) {
		// Add the user list under "users"
		model.addAttribute("requests", poolRequests.getAllRequests());
		return "displays/full_request_list";
	}

	/*****************************************************************************************
	 * This method will be called when a request is accepted. Once a request is
	 * being accepted, 1. Remove current user from the vacancy table 2. Add new
	 * user to the vacancy table 3. Remove choice from current user table 4.
	 * Update choice for the new user 5. Update request status in new user table
	 * 5. Update request status as attended
	 ****************************************************************************************/
	@RequestMapping(value = "/request/accept/{indexNumber}/{vacancyID}/{id}", method = RequestMethod.POST)
	public String acceptRequests(Model model, RedirectAttributes redirects,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("vacancyID") int vacancyID,
			@PathVariable("id") int id) {
		// Get the new applicant and current applicant
		Applicant newApplicant = poolApplicants.fetchApplicant(indexNumber);
		Applicant currentApplicant = poolApplicants.fetchApplicant(poolVacancies.getApplicant(vacancyID));
		// Check which choice is left for new applicant
		int newChoice = newApplicant.leftChoice();
		if (newChoice > 0) {
			int oldChoice = poolVacancies.getChoice(vacancyID);
			// Remove current user and add new user
			poolVacancies.changeApplicant(indexNumber, vacancyID, newChoice);
			// Update current user's choice
			poolApplicants.removeChoice(currentApplicant.getIndexNumber(), oldChoice);
			poolApplicants.setChoice(indexNumber, newChoice, vacancyID);
			// Set request status to attended
			poolRequests.deleteRequest(id);
			// Delete request from user table
			poolApplicants.deleteRequest(indexNumber);
			// Pass down the success message
			redirects.addFlashAttribute("msg", "Vacancy " + vacancyID + " for " + indexNumber + " accepted!");
			redirects.addFlashAttribute("css", "info");
			return "redirect:/admin/view_requests";
		} else {
			redirects.addFlashAttribute("msg", "User choices are full!");
			redirects.addFlashAttribute("css", "warning");
			return "redirect:/admin/view_requests";
		}
	}

	/*****************************************************************************************
	 * This method will be called when a request is cancelled. When canceling a
	 * request, 1. Remove the request status in user table 2. Update the request
	 * status as attended
	 ****************************************************************************************/
	@RequestMapping(value = "/request/decline/{indexNumber}/{id}", method = RequestMethod.POST)
	public String declineRequests(Model model, RedirectAttributes redirects,
			@PathVariable("indexNumber") String indexNumber, @PathVariable("id") int id) {
		// Delete from user table
		poolApplicants.deleteRequest(indexNumber);
		// Mark request as attended
		poolRequests.deleteRequest(id);
		// Pass down the success message
		redirects.addFlashAttribute("msg", "Request by " + indexNumber + " rejected!");
		redirects.addFlashAttribute("css", "danger");
		return "redirect:/admin/view_requests";
	}

	/*****************************************************************************************
	 * This method will be called when the administrator wants to view a company
	 ****************************************************************************************/
	@RequestMapping(value = "/company/view/{loginID}", method = RequestMethod.POST)
	public String viewCompany(Model model, RedirectAttributes redirects, @PathVariable("loginID") String loginID) {
		// Get the new applicant and current applicant
		Company company = poolCompanies.fetchCompany(loginID);
		redirects.addFlashAttribute("msg", "Company " + company.getCompany());
		redirects.addFlashAttribute("css", "info");
		return "redirect:/admin/view_companies";
	}

	/*****************************************************************************************
	 * This method will be called when the administrator wants to delete a
	 * company. When deleting a company, 1. Delete company from company table 2.
	 * Delete company from password table 3. Delete vacancies from vacancy table
	 * 4. Update choices for applicants
	 ****************************************************************************************/
	@RequestMapping(value = "/company/delete/{loginID}", method = RequestMethod.POST)
	public String deleteCompany(Model model, RedirectAttributes redirects, @PathVariable("loginID") String loginID) {
		// List all the vacancies of the company
		List<Vacancy> list = poolVacancies.getCompanyVacancies(loginID);
		// Delete choices
		for (Vacancy vacancy : list) {
			poolApplicants.removeChoice(vacancy.getApplicant(), vacancy.getChoice());
			// Delete vacancies
			poolVacancies.deleteVacancy(vacancy.getId());
		}
		// Delete password
		poolPW.deletePassword(loginID);
		// Delete Company
		poolCompanies.deleteCompany(loginID);
		// Submit success message
		redirects.addFlashAttribute("msg", "Company deleted!");
		redirects.addFlashAttribute("css", "danger");
		return "redirect:/admin/view_companies";
	}
}
