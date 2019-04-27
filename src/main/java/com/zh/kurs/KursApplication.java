package com.zh.kurs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zh.kurs.data.Category;
import com.zh.kurs.data.Question;
import com.zh.kurs.data.Subject;
import com.zh.kurs.data.UserType;
import com.zh.kurs.data.UsersFactory;
import com.zh.kurs.data.Variant;
import com.zh.kurs.data.repositories.CategoryRepository;
import com.zh.kurs.data.repositories.QuestionRepository;
import com.zh.kurs.data.repositories.UserRepository;

@SpringBootApplication
public class KursApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(CategoryRepository catRep,
			QuestionRepository questionRep, UserRepository userRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				catRep.save(new Category("math", new Subject[] {new Subject("calc"), new Subject("algebra")} ) );
				catRep.save(new Category("language", new Subject[] 
						{
							new Subject("eng", 
									new Question[] { new Question("english?", new Variant[] 
											{new Variant("yes", true), new Variant("no") } ),
									new Question("test?", new Variant[] 
										{new Variant("yes", true), new Variant("no") } ) } ),
							new Subject("rus")
						}
				) );
				userRepo.save(UsersFactory.getInstance().createUser(UserType.STUDENT, "John", "1234"));
				userRepo.save(UsersFactory.getInstance().createUser(UserType.TEACHER, "Bill", "12345"));
			}
		};
	}
	
}
