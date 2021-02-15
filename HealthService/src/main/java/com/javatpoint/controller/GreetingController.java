package com.javatpoint.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

	WebDao userDao = new WebDao(); 
	@GetMapping("/healthServices")
	public String greetingForm(Model model) {
	  model.addAttribute("greeting", new Greeting());
	  model.addAttribute("allServices",  userDao.getAllServices(""));
	  return "healthServices";
	}

	@PostMapping("/healthServices")
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
	  model.addAttribute("greeting", greeting);
	  model.addAttribute("allServices",  userDao.getAllServices(greeting.getContent()));
	  return "result";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") String id, Model model) {
		 List<Services> allServices = userDao.getAllServices("");
		userDao.deleteSelectedService(id,allServices);
		model.addAttribute("greeting", new Greeting());
		model.addAttribute("allServices", allServices);
	    return "healthServices";
	}
}
