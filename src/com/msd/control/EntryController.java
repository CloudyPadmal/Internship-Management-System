package com.msd.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msd.registers.LoginInfo;

/******************************************************************************
 * EntryController will handle the entry point. It will redirect user to the
 * "User" login page or the "Company" login page
 ******************************************************************************/
@Controller
public class EntryController {

	// ##/MSDProject/
	@RequestMapping("/")
	public String loadIndex(ModelMap map) {
		return "index";
	}

	// ##/MSDProject/user_login
	@RequestMapping(value = "/user_login", method = RequestMethod.GET)
	public ModelAndView loadUserLogin(HttpServletRequest request) {
		ModelAndView loginModel = new ModelAndView();
		loginModel.setViewName("logins/login");
		loginModel.addObject("command", new LoginInfo());
		loginModel.addObject("action_url", new String("user/log"));
		loginModel.addObject("principal", "User");
		loginModel.addObject("type", false);
		loginModel.addObject("admin", false);
		return loginModel;
	}

	// ##/MSDProject/company_login
	@RequestMapping("/company_login")
	public ModelAndView loadCompanyLogin() {
		ModelAndView loginModel = new ModelAndView();
		loginModel.setViewName("logins/login");
		loginModel.addObject("command", new LoginInfo());
		loginModel.addObject("action_url", new String("company/log"));
		loginModel.addObject("principal", "Company");
		loginModel.addObject("type", true);
		loginModel.addObject("admin", false);
		return loginModel;
	}
}
