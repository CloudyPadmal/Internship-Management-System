package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msd.registers.LoginInfo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping(value = "/log_user", method = RequestMethod.POST)
	public String logUserIn(LoginInfo info, ModelMap model) {
		System.out.println("At Log user");
		model.addAttribute("username", info.getUsername());
		model.addAttribute("password", info.getencodedPassword());
		return "result";
	}
	
	@RequestMapping("/user_list")
	public ModelAndView loadUsersList() {
		return new ModelAndView("displays/user_list");
	}	
}
