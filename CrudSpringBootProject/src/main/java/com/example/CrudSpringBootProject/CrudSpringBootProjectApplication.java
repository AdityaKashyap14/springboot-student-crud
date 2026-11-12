package com.example.CrudSpringBootProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudSpringBootProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudSpringBootProjectApplication.class, args);
		System.out.println("H");
	}

}
