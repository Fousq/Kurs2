package com.zh.kurs.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zh.kurs.data.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
	public Iterable<Subject> findAllBySubjectCategoryId(Long category_id);
}
