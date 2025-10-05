package com.yourorg.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourorg.payment.model.PaymentDetails;
import com.yourorg.payment.service.PaymentService;
@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	@PostMapping("/addPayment")
	public String addPayment(@RequestBody PaymentDetails details) {
		 System.out.println("Received payment: " + details);  // <-- add this
		    service.addPaymentDetails(details);
		    return "payment added successfully";
	}

}
