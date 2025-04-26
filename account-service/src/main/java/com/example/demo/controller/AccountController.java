package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return account;
	}

}
