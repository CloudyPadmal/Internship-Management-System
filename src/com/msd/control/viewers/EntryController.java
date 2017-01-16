package com.msd.control.viewers;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.LoginInfo;
import com.msd.pool.items.PoolPasswords;

/******************************************************************************
 * EntryController will handle the entry point. It will redirect user to the
 * "User" login page or the "Company" login page
 ******************************************************************************/
@Controller
@RequestMapping("/")
public class EntryController {

	private @Autowired AuthenticationManager authenticationManager;
	private @Autowired PoolPasswords poolPW;
	
	/*****************************************************************************************
	 * This method will displays the index page where it contains two buttons
	 * for user and company login
	 ****************************************************************************************/
	@RequestMapping("/")
	public String loadIndex(ModelMap map) {
		return "index";
	}

	/*****************************************************************************************
	 * Once a user clicks on user login, he will be directed to here in return
	 * will be shown the user login page. User status will be set to "not a
	 * company" which is FALSE.
	 ****************************************************************************************/
	@RequestMapping(value = "/user_login")
	public ModelAndView loadUserLogin() {
		ModelAndView loginModel = new ModelAndView();
		LoginInfo info = new LoginInfo();
		info.setCompany(false);
		loginModel.setViewName("logins/main_login");
		loginModel.addObject("info", info);
		loginModel.addObject("action_url", "userLogin");
		loginModel.addObject("register_url", "reg/user/");
		loginModel.addObject("principal", "User");
		loginModel.addObject("admin", false);
		return loginModel;
	}
	
	/*****************************************************************************************
	 * This method will be called when a user clicks LOG IN with user login ID
	 * and password. The passed down values (user name, password) then will be
	 * checked for validity with poolPW. If the credentials are invalid, view
	 * will be redirected to the login page with a warning message. If they are
	 * valid, it will be redirected to the user home page.
	 ****************************************************************************************/
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String logUserIn(@ModelAttribute("info") LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		try {
			// Makes the user active
			poolPW.makeActive(info.getUsername());
			// Get authentication details
			Authentication request = new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());
			// Authenticate. If it fails, will throw an exception
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			redirects.addFlashAttribute("msg", "Logged in successfully!");
			redirects.addFlashAttribute("css", "info");
			return "redirect:/user/view/" + info.getUsername();
		} catch (AuthenticationException ex) {
			redirects.addFlashAttribute("msg", "Username or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/user_login";
		}
	}

	/*****************************************************************************************
	 * Once a company user clicks on company login, he will be directed to here
	 * in return will be shown the company login page. User status will be set
	 * to "A company" which is TRUE.
	 ****************************************************************************************/
	@RequestMapping(value = "/company_login")
	public ModelAndView loadCompanyLogin() {
		ModelAndView loginModel = new ModelAndView();
		LoginInfo info = new LoginInfo();
		info.setCompany(true);
		loginModel.setViewName("logins/main_login");
		loginModel.addObject("info", info);
		loginModel.addObject("action_url", "companyLogin");
		loginModel.addObject("register_url", "reg/company/");
		loginModel.addObject("principal", "Company");
		loginModel.addObject("admin", false);
		return loginModel;
	}
	
	/*****************************************************************************************
	 * This method will be called when a company clicks LOG IN with company
	 * login ID and password. The passed down values (user name, password) then
	 * will be checked for validity with poolPW. If the credentials are invalid,
	 * view will be redirected to the login page with a warning message. If they
	 * are valid, it will be redirected to the company home page.
	 ****************************************************************************************/
	@RequestMapping(value = "/companyLogin", method = RequestMethod.POST, params = "login")
	private String companyLogin(LoginInfo info, ModelMap model, RedirectAttributes redirects) {
		try {
			// Makes the user active
			poolPW.makeActive(info.getUsername());
			// Get authentication details
			Authentication request = new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());
			// Authenticate. If it fails, will throw an exception
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			redirects.addFlashAttribute("msg", "Logged in successfully!");
			redirects.addFlashAttribute("css", "info");
			return "redirect:/company/view/" + info.getUsername();
		} catch (AuthenticationException ex) {
			// Password and credentials don't match. Redirect to login page.
			redirects.addFlashAttribute("msg", "Company or Password is wrong!");
			redirects.addFlashAttribute("css", "danger");
			return "redirect:/company_login";
		}
	}
	
	/*****************************************************************************************
	 * This method will be called when a user wants to logout. He will be 
	 * made inactive from the password table
	 ****************************************************************************************/
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logOut(Model model, HttpServletRequest request, RedirectAttributes redirects) {
		// Inactive means deactivate user completely, not during the session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		poolPW.makeInactive(auth.getName());
	    if (auth != null) {    
	        new SecurityContextLogoutHandler().logout(request, null, auth);
	    }
		// Pass the successful message to redirect
		redirects.addFlashAttribute("msg", "Logged out!");
		redirects.addFlashAttribute("css", "info");
		return "redirect:/";
	}
}
