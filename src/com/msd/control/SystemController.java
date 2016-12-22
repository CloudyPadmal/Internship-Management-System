package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {
	
	@RequestMapping("/index")
	public String loadIndex(ModelMap map) {
		return "index";
	}
	
	@RequestMapping("/user_login")
    public ModelAndView loadUserLogin(){
        return new ModelAndView("logins/user_login");
    }
	
	@RequestMapping("/company_login")
    public ModelAndView loadCompanyLogin(){
        return new ModelAndView("logins/company_login");
    }	
}
