package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.model.Account;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@GetMapping
	public Customer getCustomers() {
		Account accs = new Account();
		ArrayList<Account> l1 = new ArrayList<>();
		accs.setAccountNumber(1);
		accs.setBalance(10);
		accs.setCustomerId(1);
		l1.add(accs);
		Customer c1 = new Customer();
		c1.setId(1);
		c1.setAge(10);
		c1.setCity("Bangalore");
		c1.setName("abc");
		c1.setAccounts(l1);
		;
		return c1;

	}

}
