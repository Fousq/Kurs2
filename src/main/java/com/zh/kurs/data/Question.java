package com.zh.kurs.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String content;
	
	@OneToMany(mappedBy="variantQuestion", cascade=CascadeType.ALL)
	private List<Variant> variants;
	
	@ManyToOne
	@JoinColumn
	private Subject questionSubject;
	
	public Question(String content, Variant...variants) {
		this.content = content;
		this.variants = Stream.of(variants).collect(Collectors.toList());
		this.variants.forEach(x -> x.setVariantQuestion(this));
	}
	
}
