package net.codejava.spring.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value={"/","/home"})
	public ModelAndView homePage(ModelAndView model) throws IOException{
		
		model.setViewName("home");
		
		return model;
	}
}
