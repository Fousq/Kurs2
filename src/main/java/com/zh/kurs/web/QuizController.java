package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.kurs.data.Question;
import com.zh.kurs.data.Result;
import com.zh.kurs.data.Subject;
import com.zh.kurs.data.User;
import com.zh.kurs.data.UserAnswerSheet;
import com.zh.kurs.data.Variant;
import com.zh.kurs.data.repositories.QuestionRepository;
import com.zh.kurs.data.repositories.ResultRepository;
import com.zh.kurs.data.repositories.SubjectRepository;
import com.zh.kurs.data.repositories.VariantRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/quiz")
@Slf4j
public class QuizController {

	private QuestionRepository questionRep;
	private VariantRepository variantRep;
	private SubjectRepository subjectRepo;
	private ResultRepository resultRepo;
	
	@Autowired
	public QuizController(QuestionRepository questionRep,
			VariantRepository variantRep, SubjectRepository subjectRepo,
			ResultRepository resultRepo) {
		this.questionRep = questionRep;
		this.variantRep = variantRep;
		this.subjectRepo = subjectRepo;
		this.resultRepo = resultRepo;
	}
	
	@ModelAttribute(name="userAnswersSheet")
	public UserAnswerSheet userAnswerSheet() {
		return new UserAnswerSheet();
	}
	
	@ModelAttribute(name="subject")
	public Subject subject(@PathVariable("subject_id") Long subject_id) {
		System.out.println(subject_id);
		return subjectRepo.findById(subject_id).get();
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
		model.addAttribute("subject_id", subject_id);
		
		return "quizPage";
	}
	
	@PostMapping("/{subject_id}")
	public String processQuiz(@ModelAttribute(value="userAnswersSheet") UserAnswerSheet userAnswersSheet,
			Model model, @AuthenticationPrincipal User user, @ModelAttribute(value="subject") Subject subject) {
		List<Variant> userVariants = new ArrayList<>();
		for (Long answerId : userAnswersSheet.getAnswers()) {
			userVariants.add(variantRep.findById(answerId).get());
		}
		Integer score = countRightAnswers(userVariants);
		resultRepo.save(new Result(score, user, subject));
		return "resultPage";
	}
	
	private List<Variant> filterByQuestion(List<Variant> variants, Question question) {
		return variants
				.stream()
				.filter(variant -> variant.getVariantQuestion().equals(question))
				.collect(Collectors.toList());
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
