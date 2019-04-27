package com.zh.kurs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserRepository userRepo;
	private PasswordEncoder encoder;
	
	@Autowired
	public RegistrationController(
			UserRepository userRepo, PasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}
	
	@ModelAttribute("form")
	public RegistrationForm registration() {
		return new RegistrationForm();
	}
	
	@GetMapping
	public String registrationForm() {
		return "registrationForm";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		System.out.println(form);
		userRepo.save(form.toUser(encoder));
		return "redirect:/login";
	}
	
}
