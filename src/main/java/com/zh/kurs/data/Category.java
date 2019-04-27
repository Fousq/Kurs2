package com.zh.kurs.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="subjectCategory", cascade=CascadeType.ALL)
	private List<Subject> subjects;
	
	public Category(String name, Subject... subjects) {
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.subjects = Stream.of(subjects).collect(Collectors.toList());
		this.subjects.forEach(x -> x.setSubjectCategory(this));
	}
	
}
