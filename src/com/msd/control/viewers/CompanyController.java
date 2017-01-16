package com.msd.control.viewers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Company;
import com.msd.items.Vacancy;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("company")
public class CompanyController implements Preferences {

	private @Autowired PoolCompanies poolCompanies;
	private @Autowired PoolVacancies poolVacancies;
	
	/*****************************************************************************************
	 * This method will be the redirected method by POST methods related to
	 * company. This will show the company home page with details and vacancies
	 ****************************************************************************************/
	@RequestMapping(value = "/view/{loginID}", method = RequestMethod.GET)
	public String companyHomePage(Model model, @PathVariable("loginID") String loginID,
			RedirectAttributes redirects) throws ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();				
		if (auth == null || !auth.getName().equals(loginID)) {
			throw new ServletException("Unauthorised");
		}
		// Fetch the applicant
		Company company = poolCompanies.fetchCompany(loginID);
		if (company == null) {
			// Password and credentials don't match. Redirect to login page.
			redirects.addFlashAttribute("msg", "Company not found!");
			redirects.addFlashAttribute("css", "warning");
			// This will not happen. But has handled it anyways
			return "redirect:/company_login";
		}
		// Load a list of vacancies posted by the company
		List<Vacancy> listOfVacancies = poolVacancies.getCompanyVacancies(company.getLoginID());
		// Add the company under "company"
		model.addAttribute("company", company);
		model.addAttribute("vacancies", listOfVacancies);
		return "displays/show_company";
	}
	

	/*****************************************************************************************
	 * This method will be called when a company wants to update himself. It
	 * will generate the company object with it's relevant attributes and pass
	 * an extra variable to identify this is an update because it's the same
	 * form for both new and update company
	 ****************************************************************************************/
	@RequestMapping(value = "/update/{loginID}", method = RequestMethod.POST)
	public String showUpdateCompanyForm(@PathVariable("loginID") String loginID, Model model) {
		// Fetch the company details from database
		Company company = poolCompanies.fetchCompany(loginID);
		// Add details under "companyForm"
		model.addAttribute("companyForm", company);
		// Notify this is an update
		model.addAttribute("new_company", false);
		return "logins/register_company";
	}
}