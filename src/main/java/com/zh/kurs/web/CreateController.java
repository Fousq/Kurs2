package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.Category;
import com.zh.kurs.data.Subject;
import com.zh.kurs.data.User;
import com.zh.kurs.data.repositories.CategoryRepository;
import com.zh.kurs.data.repositories.QuestionRepository;
import com.zh.kurs.data.repositories.SubjectRepository;
import com.zh.kurs.data.repositories.VariantRepository;

@Controller
@RequestMapping("/create")
public class CreateController {
	
	private CategoryRepository categoryRepo;
	private SubjectRepository subjectRepo;
	private QuestionRepository questionRepo;
	private VariantRepository variantRepo;
	
	public CreateController(CategoryRepository categoryRepo,
			SubjectRepository subjectRepo, QuestionRepository questionRepo,
			VariantRepository variantRepo) {
		this.categoryRepo = categoryRepo;
		this.subjectRepo = subjectRepo;
		this.questionRepo = questionRepo;
		this.variantRepo = variantRepo;
	}
	
	@GetMapping
	public String showCreatePage(@AuthenticationPrincipal User user) {
		if (!user.isTeacher()) {
			return "redirect:/";
		}
		List<Category> categories = new ArrayList<>();
		List<Subject> subjects = new ArrayList<>();
		categoryRepo.findAll().forEach(category -> categories.add(category));
		subjectRepo.findAll().forEach(subject -> subjects.add(subject));
		return "createPage";
	}
	
	@PostMapping
	public String processCreate() {
		return "redirect:/";
	}
	
}
