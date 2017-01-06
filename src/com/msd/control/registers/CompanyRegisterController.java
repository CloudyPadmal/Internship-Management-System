package com.msd.control.registers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Company;
import com.msd.items.LoginInfo;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.validators.PoolCompanyValidator;

@Controller
@RequestMapping("reg/company")
public class CompanyRegisterController implements Preferences {

	@Autowired
	PoolCompanyValidator companyValidator;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolPasswords poolPW;

	/*****************************************************************************************
	 * This method is the validator binder. It will bind the company details
	 * validator to the registration page when a update or a new register is
	 * happening
	 ****************************************************************************************/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(companyValidator);
	}

	/*****************************************************************************************
	 * This method will be called when a new company wants to register. It will
	 * generate the registration form
	 ****************************************************************************************/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String registerCompany(Model model) {
		// Pass the Applicant as "userForm"
		model.addAttribute("companyForm", new Company());
		// Notify this is an update
		model.addAttribute("new_company", true);
		// Display the registration form
		return "logins/register_company";
	}

	/*****************************************************************************************
	 * When a new company has been created, this method will be loaded. It will
	 * produce a success message if the update is complete. Or a redirect to the
	 * register page to fix the mistakes. When registering a company, 1. Add
	 * company to the company table 2. Add company password to the password
	 * table
	 ****************************************************************************************/
	@RequestMapping(value = "companies", method = RequestMethod.POST, params = "create")
	public String addsCompany(@ModelAttribute("companyForm") @Validated Company company, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors() || company.isNotOK()) {
			return "logins/register_company";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Welcome " + company.getCompany());
			// Add company to the company table
			poolCompanies.addCompany(company);
			// Add password of the company to the password table
			poolPW.addPassword(new LoginInfo(company.getLoginID(), company.getPassword(), true));
			// Display Company details
			return "redirect:/company/view/" + company.getLoginID();
		}
	}

	/*****************************************************************************************
	 * When a company has changed the details and clicks UPDATE, this method
	 * will be loaded. It will produce a success message if the update is
	 * complete. Or a redirect to the edit page to fix the mistakes
	 ****************************************************************************************/
	@RequestMapping(value = "companies", method = RequestMethod.POST, params = "update")
	public String updatesCompany(@ModelAttribute("companyForm") @Validated Company company, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors() || company.isNotOK()) {
			return "logins/register_company";
		} else {
			// Pass success message to redirect view
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Company updated successfully!");
			// Add company to the company table
			poolCompanies.updateCompany(company);
			// Display Company details
			return "redirect:/company/view/" + company.getLoginID();
		}
	}
}
