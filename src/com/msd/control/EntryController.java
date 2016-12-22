package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msd.registers.LoginInfo;

@Controller
public class EntryController {

	@RequestMapping("/")
	public String loadIndex(ModelMap map) {
		return "index";
	}

	@RequestMapping("/user_login")
	public ModelAndView loadUserLogin() {
		return new ModelAndView("logins/user_login", "command", new LoginInfo());
	}

	@RequestMapping("/company_login")
	public ModelAndView loadCompanyLogin() {
		return new ModelAndView("logins/company_login", "command", new LoginInfo());
	}
}
