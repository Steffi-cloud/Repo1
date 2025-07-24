package com.example.demo;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class SpringBootHazelCastKafkaApplication {
	
	
	private Logger log = LoggerFactory.getLogger(SpringBootHazelCastKafkaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootHazelCastKafkaApplication.class, args);
	}
	
	

}
