package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * curl -X POST http://localhost:1000/orders ^
More?  -H "Content-Type: application/json" ^
More?  -d "{\"orderID\":\"12345\",\"dateOfCreation\":\"2025-07-29T11:45:00\",\"content\":\"Sample order content\"}"
 */
@SpringBootApplication
public class KafkasampleproducerconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkasampleproducerconsumerApplication.class, args);
	}

}
