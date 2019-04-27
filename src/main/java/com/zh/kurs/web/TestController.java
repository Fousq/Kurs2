package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.Category;
import com.zh.kurs.data.Subject;
import com.zh.kurs.data.User;
import com.zh.kurs.data.repositories.CategoryRepository;
import com.zh.kurs.data.repositories.SubjectRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tests")
@Slf4j
public class TestController {
	
	private CategoryRepository catRep;
	private SubjectRepository subjectRep;
	
	@Autowired
	public TestController(CategoryRepository catRep,
			SubjectRepository subjectRep) {
		this.catRep = catRep;
		this.subjectRep = subjectRep;
	}
	
	
	@GetMapping
	public String showTestsPage(Model model, @AuthenticationPrincipal User user) {
		List<Category> categories = new ArrayList<>();
		catRep.findAll().forEach(x -> categories.add(x));
		System.out.println(user);
		model.addAttribute("categories", categories);
		
		return "testPage";
	}
	
	@GetMapping("/{category_id}")
	public String showSubjectsPage(@PathVariable("category_id") Long category_id,
			Model model) {
		List<Subject> subjects = new ArrayList<>();
		subjectRep.findAllBySubjectCategoryId(category_id).forEach(x -> subjects.add(x));
		
		model.addAttribute("subjects", subjects);
		
		return "subjectsPage";
	}
	
}
