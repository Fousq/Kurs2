package com.zh.kurs.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zh.kurs.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
}
