package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootLoggingAndSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoggingAndSchedulingApplication.class, args);
	}

}
