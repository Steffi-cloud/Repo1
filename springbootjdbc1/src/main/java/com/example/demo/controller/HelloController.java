package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.CountryDAOImpl;
@RestController
public class HelloController {
	@Autowired
	CountryDAOImpl countryImpl;

	@GetMapping("/hello")
	public Map<String, Object> sayHello() {

	countryImpl.execute();
	return countryImpl.execute();
	}
}
