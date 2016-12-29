package com.msd.control;


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

import com.msd.pool.PoolCompanies;
import com.msd.pool.PoolPasswords;
import com.msd.pool.validators.PoolCompanyValidator;
import com.msd.poolinterfaces.Preferences;
import com.msd.registers.LoginInfo;
import com.msd.users.Company;

@Controller
@RequestMapping("reg/company")
public class CompanyRegisterController implements Preferences {

	@Autowired
	PoolCompanyValidator companyValidator;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolPasswords poolPW;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(companyValidator);
	}

	// Register form for a new company
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String registerCompany(Model model) {
		// Pass the Applicant as "userForm"
		model.addAttribute("companyForm", new Company());
		// Display the html page
		return "logins/register_company";
	}

	// All companies in the table
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public String showAllCompanies(Model model) {
		// Add the user list under "users"
		model.addAttribute("companies", poolCompanies.getAllCompanies());
		return "displays/company_list";
	}

	// Delete Company
	@RequestMapping(value = "/company/{index}/delete", method = RequestMethod.GET)
	public String deleteCompany(@PathVariable("index") String companyName, final RedirectAttributes redirectAttributes) {
		poolCompanies.deleteCompany(companyName);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("msg", "Company deleted!");
		return "redirect:/reg/company/companies";
	}

	// Display Company details
	@RequestMapping(value = "/company/{index}", method = RequestMethod.GET)
	public String showCompany(@PathVariable("index") String companyName, Model model) {
		// Fetch applicant from database
		Company company = poolCompanies.fetchCompany(companyName);
		if (company == null) {
			// If there is no user, return a failure message
			model.addAttribute("msg", "Company not found");
		}
		// Add the company under "company"
		model.addAttribute("company", company);
		return "displays/show_company";
	}

	// This will be called upon clicking register button
	@RequestMapping(value = "companies", method = RequestMethod.POST)
	public String addCompany(@ModelAttribute("companyForm") @Validated Company company, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "logins/register_company";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("msg", "Company added successfully!");
			// Add company to the company table
			poolCompanies.addCompany(company);
			// Add password of the company to the password table
			poolPW.addPassword(new LoginInfo(company.getLoginID(), company.getPassword(), true));
			// Display Company details
			return "redirect:company/" + company.getCompany();
		}
	}

	// Display Update Form
	@RequestMapping(value = "/company/{id}/update", method = RequestMethod.GET)
	public String showUpdateCompanyForm(@PathVariable("id") String id, Model model) {
		// Fetch the company details from database
		Company company = poolCompanies.fetchCompany(id);
		// Add details under "companyForm"
		model.addAttribute("companyForm", company);
		return "logins/register_company";
	}
}
