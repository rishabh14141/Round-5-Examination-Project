package com.example.examination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ExaminationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminationApplication.class, args);
	}

}
