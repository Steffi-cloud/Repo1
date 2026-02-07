package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tutorial;
import com.example.demo.service.TutorialService;

@RestController
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * @RequestMapping("/api") public String getTheValue() {
	 * logger.info("calling service"); return "index"; }
	 */

	@GetMapping("/hello")
	public Collection<String> sayHello() {
		logger.info("bye service");
		return IntStream.range(0, 10).mapToObj(i -> "Hello number " + i).collect(Collectors.toList());
	}

	@Autowired
	private TutorialService service;

	// private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/saveTutorial")
	public Tutorial saveTutorial(@RequestBody Tutorial tutorial) {
		logger.info("calling  save service");
		return service.saveTutorial(tutorial);

	}

	@GetMapping("/getTutorial")
	public List<Tutorial> getTutorials() {
		logger.info("calling get all tutorials service");
		return service.getAllTutorials();

	}
	
	@GetMapping("/getTutorialById")
	public Tutorial getTutorialById( @RequestParam UUID id) {
		logger.info("calling get tutorial by id service");
		return service.getTutorialById(id);

	}
	
	
	@PutMapping("/updateTutorial")
	public Tutorial updateTutorial(@RequestBody Tutorial tutorial) {
		logger.info("calling  save service");
		return service.saveTutorial(tutorial);

	}
	@PutMapping("/updateAllTutorials")
	public List<Tutorial> updateAllTutorials(@RequestBody List<Tutorial> tutorial) {
		logger.info("calling  save service");
		return service.updateAllTutorials(tutorial);

	}

}
