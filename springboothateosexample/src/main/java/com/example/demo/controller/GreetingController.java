package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Greeting;
/**
 * 
 * 
 * 
 * http://localhost:1111/greeting
 * 
 * 
 * 
 * 
 * {
  "content": "Hello, World!",
  "_links": {
    "self": [
      {
        "href": "http://localhost:1111/greeting?name=World"
      },
      {
        "href": "http://localhost:1111/string"
      }
    ]
  }
}
 */
@RestController
public class GreetingController {
	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping("/greeting")
	public HttpEntity<Greeting> greeting(
		@RequestParam(value = "name", defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
		greeting.add(linkTo(methodOn(GreetingController.class).getString()).withSelfRel());
				//withRel("http://localhost:1111/string"));
		

		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
	
	
	@RequestMapping("/string")
	public ResponseEntity<String> getString(
		//@RequestParam(value = "name", defaultValue = "World") String name
		) {

		//Greeting greeting = new Greeting(String.format(TEMPLATE, "abc"));
		//greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
	//	greeting.add(linkTo(methodOn(GreetingController.class).getString()));

		return new ResponseEntity<String>("abc", HttpStatus.OK);
	}
}
