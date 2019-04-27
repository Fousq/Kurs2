package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.Question;
import com.zh.kurs.data.UserAnswerSheet;
import com.zh.kurs.data.Variant;
import com.zh.kurs.data.repositories.QuestionRepository;
import com.zh.kurs.data.repositories.VariantRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/quiz")
@Slf4j
public class QuizController {

	private QuestionRepository questionRep;
	private VariantRepository variantRep;
	
	@Autowired
	public QuizController(QuestionRepository questionRep,
			VariantRepository variantRep) {
		this.questionRep = questionRep;
		this.variantRep = variantRep;
	}
	
	@ModelAttribute(name="userAnswersSheet")
	public UserAnswerSheet userAnswerSheet() {
		return new UserAnswerSheet();
	}
	
	@GetMapping("/{subject_id}")
	public String showQuizPage(@PathVariable("subject_id") Long subject_id,
			Model model) {
		HashMap<Question, List<Variant>> sections = 
				new HashMap<>(); 
		List<Question> questions = new ArrayList<>();
		List<Variant> variants = new ArrayList<>();
		questionRep.findAllByQuestionSubjectId(subject_id)
			.forEach(x -> questions.add(x));
		for (Question question : questions) {
			variantRep.findAllByVariantQuestionId(question.getId()).
				forEach(variant -> variants.add(variant));
			sections.put(question, filterByQuestion(variants, question));
		}
		
		model.addAttribute("sections", sections);
		
		return "quizPage";
	}
	
	private List<Variant> filterByQuestion(List<Variant> variants, Question question) {
		return variants
				.stream()
				.filter(variant -> variant.getVariantQuestion().equals(question))
				.collect(Collectors.toList());
	}
	
}
