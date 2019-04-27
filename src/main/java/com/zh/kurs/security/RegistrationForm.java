package com.zh.kurs.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.zh.kurs.data.User;
import com.zh.kurs.data.UserType;
import com.zh.kurs.data.UsersFactory;

import lombok.Data;

@Data
public class RegistrationForm {
	
	private String username;
	private String password;
	private UserType userType;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return UsersFactory
				.getInstance()
				 .createUser(userType, username, 
					passwordEncoder.encode(password));
	}
	
}
