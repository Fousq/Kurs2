package com.zh.kurs.web;

import java.util.ArrayList;
import java.util.List;

import com.zh.kurs.data.User;

public class UserObserver {
	
	private List<User> users = new ArrayList<>();
	
	public void add(User user) {
		users.add(user);
		update(user);
	}
	
	public void remove(User user) {
		users.remove(user);
	}
	
	public void update(User user) {
		user.update(true);
	}
	
}
