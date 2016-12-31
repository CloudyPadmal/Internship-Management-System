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

import com.msd.items.Company;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("company")
public class CompanyController {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolVacancies poolVacancies;

	// This view will display the correctness of the user credentials
	@RequestMapping(value = "/log", method = RequestMethod.POST, params = "login")
	public String logUserIn(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
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
		poolPW.addPassword(info);
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
}