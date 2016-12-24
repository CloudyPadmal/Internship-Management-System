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
		ModelAndView loginModel = new ModelAndView();
		loginModel.setViewName("logins/login");
		loginModel.addObject("command", new LoginInfo());
		loginModel.addObject("action_url", new String("log_user"));
		loginModel.addObject("principal", "Admin");
		loginModel.addObject("type", true);
		loginModel.addObject("admin", true);
		return loginModel;
	}
	
	@RequestMapping(value = "/log_user", method = RequestMethod.POST)
	public String logUserIn(LoginInfo info, ModelMap model) {
		System.out.println("At admin user");
		model.addAttribute("username", info.getUsername());
		model.addAttribute("password", info.getencodedPassword());
		return "result";
	}
}
