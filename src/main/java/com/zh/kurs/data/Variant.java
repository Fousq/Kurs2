package com.zh.kurs.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Variant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String content;
	private boolean isAnswer = false;
	
	@ManyToOne
	@JoinColumn
	private Question variantQuestion;
	
	public Variant(String content, boolean isAnswer, Question question) {
		this.content = content;
		this.isAnswer = isAnswer;
		variantQuestion = question;
	}
	
	public Variant(String content, boolean isAnswer) {
		this.content = content;
		this.isAnswer = isAnswer;
	}
	
	public Variant(String content, Question question) {
		this.content = content;
		variantQuestion = question;
	}
	
	public Variant(String content) {
		this.content = content;
	}

	public Variant() { }
	
}
