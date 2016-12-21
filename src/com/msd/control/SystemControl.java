package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemControl {

	@RequestMapping("/login")
	public ModelAndView showform() {
		return new ModelAndView("login");
	}
}
