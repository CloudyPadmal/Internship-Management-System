package com.msd.control.viewers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Company;
import com.msd.items.LoginInfo;
import com.msd.items.Vacancy;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCompanies;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;

@Controller
@RequestMapping("company")
public class CompanyController implements Preferences {

	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolCompanies poolCompanies;
	@Autowired
	PoolVacancies poolVacancies;

	/*****************************************************************************************
	 * This method will be called when a company clicks LOG IN with company
	 * login ID and password. The passed down values (user name, password) then
	 * will be checked for validity with poolPW. If the credentials are invalid,
	 * view will be redirected to the login page with a warning message. If they
	 * are valid, it will be redirected to the company home page.
	 ****************************************************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "login")
	private String companyLogin(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		if (poolPW.matchThisAndThat(info)) {
			// Password and the credentials are matching. Load company details
			redirects.addFlashAttribute("msg", "Logged in successfully!");
			redirects.addFlashAttribute("css", "info");
			return "redirect:/company/view/" + info.getUsername();
		} else {
			// Password and credentials don't match. Redirect to login page.
			redirects.addFlashAttribute("msg", "Company or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/company_login";
		}
	}
	
	
	/*****************************************************************************************
	 * This method will be the redirected method by POST methods related to
	 * company. This will show the company home page with details and vacancies
	 ****************************************************************************************/
	@RequestMapping(value = "/view/{loginID}", method = RequestMethod.GET)
	public String companyHomePage(Model model, @PathVariable("loginID") String loginID,
			RedirectAttributes redirects) {
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
		for (Vacancy vacancy : listOfVacancies) {
			vacancy.setCompanyName(poolCompanies.getCompanyName(vacancy.getCompanyID()));
		}
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