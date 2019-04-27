package com.zh.kurs.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.User;

@Controller
@RequestMapping("/create")
public class CreateController {
	
	@GetMapping
	public String showCreatePage(@AuthenticationPrincipal User user) {
		if (!user.isTeacher()) {
			return "redirect:/";
		}
		
		return "createPage";
	}
	
}
