package com.yourorg.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yourorg.payment.model.MultipleSubscriptionRequest;
import com.yourorg.payment.model.PaymentDetails;
//import com.yourorg.payment.model.SubscriptionRequest;
import com.yourorg.payment.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {

	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	private PaymentService service;

	@PostMapping("/addPayment")
	public String addPayment(@RequestBody PaymentDetails details) {
		System.out.println("Received payment: " + details); // <-- add this
		service.addPaymentDetails(details);
		logger.info("addPaymentDetails method called");
		return "payment added successfully";
	}

	@GetMapping("/subscriptions/fetchUserPaymentHistory")
	public PaymentDetails getAllPaymentsOfUser(@RequestParam(name = "userId") Integer userId) {
		logger.info("getAllSubscriptions method called");
		return service.getAllSubscriptions(userId);
	}

	@PutMapping("/payments")
	public PaymentDetails updatePaymentMode(@RequestParam(name = "userId") Integer userId,
			@RequestBody PaymentDetails details) {
		logger.info("updatePaymentMode method called");
		return service.updatePaymentMode(userId, details);
	}
	
	@PostMapping("/subscriptions/multiple")
	public ResponseEntity<String> addMultipleSubscriptions(
            @RequestBody MultipleSubscriptionRequest request) {

		service.addMultipleSubscriptions(request.getSubscriptions());
        return ResponseEntity.status(HttpStatus.CREATED).body("Subscriptions added successfully");
    }


}
