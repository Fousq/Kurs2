package com.zh.kurs.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zh.kurs.data.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{
	public Iterable<Question> findAllByQuestionSubjectId(Long subject_id); 
}
