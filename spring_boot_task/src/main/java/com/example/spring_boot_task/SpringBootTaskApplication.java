package com.example.spring_boot_task;

import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SpringBootTaskApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootTaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository repository) {
		return (args) -> {
			repository.save(new User("Jack", "Bauer",35));
			repository.save(new User("Chloe", "O'Brian",46));
			repository.save(new User("Kim", "Bauer",30));


			repository.findAll().forEach(user -> {
				log.info("User: {}", user);
			});
		};
	}

}
