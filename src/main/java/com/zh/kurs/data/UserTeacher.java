package com.zh.kurs.data;

import javax.persistence.Entity;

@Entity
public class UserTeacher extends User {
	
	public UserTeacher() { }
	
	public UserTeacher(String name, String password) {
		super(name, password, true);
	}
	
}
