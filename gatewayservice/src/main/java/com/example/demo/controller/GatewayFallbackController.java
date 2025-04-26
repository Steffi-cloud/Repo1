package com.example.demo.controller;

import org.springframework.http.HttpStatus;
//import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/fallback")
public class GatewayFallbackController {
	  @GetMapping("/account")
	    public ResponseEntity<String> getAccount() {
	      System.out.println("In account fallback");
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body("There is a problem in AccountService, please try after some time");
	    }
	    
	  @GetMapping("/customer")
	    public ResponseEntity<String> getCustomer() {
	      System.out.println("In customer fallback");
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body("There is a problem in CustomerService, please try after some time");
	    }
}
