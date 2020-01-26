package com.neo.mvc.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InternalJspController {

	@GetMapping("/innerView/{viewname}")
	public ModelAndView visitInternalJsp(
			@PathVariable(name="viewname") String viewname
			) {
		ModelAndView mv = new ModelAndView(viewname);
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		
		mv.addObject("time", c.toString());
		return mv;
	}
	
}
