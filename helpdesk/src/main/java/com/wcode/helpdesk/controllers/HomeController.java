package com.wcode.helpdesk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
		return "home/index";
	}

	@GetMapping("/denied")
	public String accessDanied(Model model) {
		return "home/403";
	}

}
