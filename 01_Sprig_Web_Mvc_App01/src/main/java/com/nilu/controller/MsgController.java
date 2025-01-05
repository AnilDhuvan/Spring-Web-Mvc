package com.nilu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MsgController {
	
	//    http://localhost:8081/welcome
	
	@GetMapping("/welcome")
	public ModelAndView getMsg1() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg1","Welcome to java City");
		mav.setViewName("index");
		return mav;
	}
	
	//http://localhost:8081/gm
	
	@GetMapping("/gm")
	public ModelAndView getMsg2() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg2", "Good Morning...!!");
		mav.setViewName("index");
		return mav;
	}

}
