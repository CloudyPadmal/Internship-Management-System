package com.msd.control.viewers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.LoginInfo;
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
	@RequestMapping(value = "/view_users", method = RequestMethod.POST)
	public String showAllUsers(Model model) {
		// Add the user list under "users"
		model.addAttribute("users", poolApplicants.getAllApplicants());
		return "displays/full_user_list";
	}

	/*****************************************************************************************
	 * This method will generate a list of all the companies and their details
	 * and passed down to display the complete list of them.
	 ****************************************************************************************/
	@RequestMapping(value = "/view_companies", method = RequestMethod.POST)
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
	@RequestMapping(value = "/view_vacancies", method = RequestMethod.POST)
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
	@RequestMapping(value = "/view_requests", method = RequestMethod.POST)
	public String showAllRequests(Model model) {
		// Add the user list under "users"
		model.addAttribute("requests", poolRequests.getAllRequests());
		return "displays/full_request_list";
	}
	
	@RequestMapping(value = "/request/accept/{indexNumber}/{vacancyID}", method = RequestMethod.POST)
	public String acceptRequests(Model model) {
		// Add the user list under "users"
		model.addAttribute("requests", poolRequests.getAllRequests());
		return "displays/full_request_list";
	}
	
	@RequestMapping(value = "/request/decline/{indexNumber}/{vacancyID}", method = RequestMethod.POST)
	public String declineRequests(Model model) {
		// Add the user list under "users"
		model.addAttribute("requests", poolRequests.getAllRequests());
		return "displays/full_request_list";
	}
}
