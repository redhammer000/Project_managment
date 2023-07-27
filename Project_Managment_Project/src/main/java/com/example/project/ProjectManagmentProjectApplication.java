package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class ProjectManagmentProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagmentProjectApplication.class, args);
		
	}

}
