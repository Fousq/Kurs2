package com.zh.kurs.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CreationForm {
	
	private Question question;
	private List<String> variants = new ArrayList<>();
	private String rightAnswer;
	
	public CreationForm() { }
	
}
