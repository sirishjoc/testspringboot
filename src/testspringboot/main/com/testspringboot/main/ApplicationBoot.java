package com.testspringboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(value = "com.testspringboot.repository")
@EnableMongoAuditing
@ComponentScan(basePackages="com.testspringboot")
public class ApplicationBoot {

	public static void main(String[] args){
		SpringApplication.run(ApplicationBoot.class, args);
	}
}
