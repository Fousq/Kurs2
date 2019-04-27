package com.zh.kurs.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class WelcomeController {
	
	@GetMapping
	public String getIndexPage(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "index";
	}
	
}
