package com.zh.kurs.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zh.kurs.data.Variant;

public interface VariantRepository extends CrudRepository<Variant, Long>{
	public Iterable<Variant> findAllByVariantQuestionId(Long question_id);
}
