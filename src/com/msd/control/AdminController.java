package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msd.registers.LoginInfo;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@RequestMapping("/")
	public ModelAndView loginAdmin() {
		return new ModelAndView("logins/admin_login", "command", new LoginInfo());
	}
	
	@RequestMapping(value = "/log_user", method = RequestMethod.POST)
	public String logUserIn(LoginInfo info, ModelMap model) {
		System.out.println("At admin user");
		model.addAttribute("username", info.getUsername());
		model.addAttribute("password", info.getencodedPassword());
		return "result";
	}
}
