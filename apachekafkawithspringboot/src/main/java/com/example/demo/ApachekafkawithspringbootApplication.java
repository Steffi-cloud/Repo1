package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
/**
 * 
 * https://javatechonline.com/how-to-work-with-apache-kafka-in-spring-boot/
 *
 */
@SpringBootApplication
@EnableKafka
public class ApachekafkawithspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApachekafkawithspringbootApplication.class, args);
	}

}
