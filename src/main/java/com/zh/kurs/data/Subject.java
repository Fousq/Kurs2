package com.zh.kurs.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn
	private Category subjectCategory;
	
	@OneToMany(mappedBy="questionSubject", cascade=CascadeType.ALL)
	private List<Question> questions;
	
	public Subject(String name, Question...questions) {
		this.name = name;
		this.questions = Stream.of(questions).collect(Collectors.toList());
		this.questions.forEach(x -> x.setQuestionSubject(this));
	}
	
}
