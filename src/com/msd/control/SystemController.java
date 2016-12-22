package com.msd.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {

	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		String message = "Hello World!!!";
		return new ModelAndView("hello", "message", message);
	}
	
	@RequestMapping("index")
	public String loadIndex(ModelMap map) {
		return "index";
	}
	
	
}