package com.zh.kurs.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Result {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer score;
	
	@OneToOne
	@JoinColumn
	private User user;
	
	@OneToOne
	@JoinColumn
	private Subject subject;
	
	public Result(Integer score, User user, Subject subject) {
		this.score = score;
		this.user = user;
		this.subject = subject;
	}
	
}
