package com.msd.control.viewers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.msd.items.LoginInfo;

/******************************************************************************
 * EntryController will handle the entry point. It will redirect user to the
 * "User" login page or the "Company" login page
 ******************************************************************************/
@Controller
public class EntryController {

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
		loginModel.addObject("action_url", "user/login");
		loginModel.addObject("register_url", "reg/user/");
		loginModel.addObject("principal", "User");
		loginModel.addObject("admin", false);
		return loginModel;
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
		loginModel.addObject("action_url", "company/login");
		loginModel.addObject("register_url", "reg/company/");
		loginModel.addObject("principal", "Company");
		loginModel.addObject("admin", false);
		return loginModel;
	}
}
