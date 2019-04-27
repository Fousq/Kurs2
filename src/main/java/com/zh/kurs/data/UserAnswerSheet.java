package com.zh.kurs.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserAnswerSheet {
	
	private List<Long> answers = new ArrayList<>();
	
	public UserAnswerSheet() { } 
	
}
