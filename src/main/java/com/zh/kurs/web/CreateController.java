package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.Category;
import com.zh.kurs.data.CreationForm;
import com.zh.kurs.data.Question;
import com.zh.kurs.data.Subject;
import com.zh.kurs.data.User;
import com.zh.kurs.data.Variant;
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
	
	@ModelAttribute(name="creationForm")
	public CreationForm creationForm() {
		return new CreationForm();
	}
	
	@GetMapping
	public String showCreatePage(@AuthenticationPrincipal User user,
			Model model) {
		if (!user.isTeacher()) {
			return "redirect:/";
		}
		List<Category> categories = new ArrayList<>();
		List<Subject> subjects = new ArrayList<>();
		HashMap<Category, List<Subject>> sections = new HashMap<>();
		for (Category category : categoryRepo.findAll()) {
			subjectRepo.findAllBySubjectCategoryId(category.getId()).forEach(subject -> subjects.add(subject));
			sections.put(category, filter(category, subjects));
		}
		
		model.addAttribute("sections", sections);
		
		return "createPage";
	}
	
	@PostMapping
	public String processCreate(@ModelAttribute(value="creationForm") CreationForm form) {
		System.out.println(form.getRightAnswer());
		Question question = form.getQuestion();
		questionRepo.save(question);
		for (String variant : form.getVariants()) {
			System.out.println(variant +  ": " + ( variant.equals( form.getRightAnswer() ) ) );
			if (variant.equals( form.getRightAnswer() ) ) {
				variantRepo.save(new Variant(variant, true, question));
			} else {
				variantRepo.save(new Variant(variant, question));
			}
		}
		return "successPage";
	}
	
	private List<Subject> filter(Category category, List<Subject> subjects) {
		return subjects.stream().filter(subject -> subject.getSubjectCategory().equals(category)).collect(Collectors.toList());
	}
	
}
