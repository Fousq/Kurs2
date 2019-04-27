package com.zh.kurs.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zh.kurs.data.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
}
