package com.biblioteca.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.biblioteca")
@EntityScan(basePackages = {"com.biblioteca.domains", "com.biblioteca.domains.enums"})
@EnableJpaRepositories(basePackages = "com.biblioteca.repositories")
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
