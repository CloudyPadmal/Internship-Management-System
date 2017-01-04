package com.msd.control.viewers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msd.items.LoginInfo;

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
	@RequestMapping(value = "/user_login")
	public ModelAndView loadUserLogin(HttpServletRequest request) {
		ModelAndView loginModel = new ModelAndView();
		LoginInfo info = new LoginInfo();
		info.setCompany(false);
		loginModel.setViewName("logins/login");
		loginModel.addObject("info", info);
		loginModel.addObject("action_url", "user/log");
		loginModel.addObject("principal", "User");
		loginModel.addObject("admin", false);
		return loginModel;
	}

	// ##/MSDProject/company_login
	@RequestMapping(value = "/company_login")
	public ModelAndView loadCompanyLogin() {
		ModelAndView loginModel = new ModelAndView();
		LoginInfo info = new LoginInfo();
		info.setCompany(true);
		loginModel.setViewName("logins/login");
		loginModel.addObject("info", info);
		loginModel.addObject("action_url", "company/log");
		loginModel.addObject("principal", "Company");
		loginModel.addObject("admin", false);
		return loginModel;
	}
}
