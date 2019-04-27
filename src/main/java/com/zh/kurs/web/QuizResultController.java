package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.UserAnswerSheet;
import com.zh.kurs.data.Variant;
import com.zh.kurs.data.repositories.VariantRepository;

@Controller
@RequestMapping("/result")
public class QuizResultController {
	
	private VariantRepository variantRepo;
	
	public QuizResultController(VariantRepository variantRepo) {
		this.variantRepo = variantRepo;
	}
	
	@PostMapping
	public String processQuiz(@ModelAttribute(value="userAnswersSheet") UserAnswerSheet userAnswersSheet) {
		System.out.println(userAnswersSheet);
		List<Variant> userVariants = new ArrayList<>();
		for (Long answerId : userAnswersSheet.getAnswers()) {
			userVariants.add(variantRepo.findById(answerId).get());
		}
		System.out.println(countRightAnswers(userVariants));
		return "resultPage";
	}
	
	private Integer countRightAnswers(List<Variant> variants) {
		int count = 0;
		for (Variant variant : variants) {
			if (variant.isAnswer()) {
				count++;
			}
		}
		return count;
	}
	
}
