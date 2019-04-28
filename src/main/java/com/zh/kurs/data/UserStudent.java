package com.zh.kurs.data;

import javax.persistence.Entity;

@Entity
public class UserStudent extends User {

	public UserStudent() { }
	
	public UserStudent(String username, String password) {
		super(username, password);
	}

}
