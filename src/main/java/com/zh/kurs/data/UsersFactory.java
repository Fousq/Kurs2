package com.zh.kurs.data;

public class UsersFactory {
	
	private static UsersFactory usersFactory = null;
	private User user;
	
	private UsersFactory() {
		
	}
	
	public static UsersFactory getInstance() {
		if (usersFactory == null) {
			usersFactory = new UsersFactory();
		}
		return usersFactory;
	}
	
	public User createUser(UserType type, String name, String password) {
		if (type == UserType.STUDENT) {
			user = new UserStudent(name, password);
		} else if (type == UserType.TEACHER) {
			user = new UserTeacher(name, password);
		} else {
			user = null;
		}
		return user;
	}
	
}
