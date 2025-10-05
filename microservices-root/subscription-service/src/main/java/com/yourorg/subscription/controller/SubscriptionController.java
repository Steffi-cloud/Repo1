
package com.yourorg.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourorg.subscription.model.MultipleSubscriptionRequest;
import com.yourorg.subscription.model.SubscriptionRequest;
import com.yourorg.subscription.model.SubscriptionUpdateRequest;
import com.yourorg.subscription.service.SubscriptionService;

@RestController

@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;

	@PostMapping("/addSubscription")
	public ResponseEntity<?> addSubscription(@RequestBody SubscriptionRequest request) {
		service.addSubscription(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	
	@PutMapping("/updateSubscription/{userId}")
	public ResponseEntity<?> updateSubscription(@PathVariable("userId") String userId,@RequestBody SubscriptionUpdateRequest request) {
		service.updateRequest(userId, request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
	
	@DeleteMapping("/removeUser/{userId}")
	public ResponseEntity<?> removeUser(@PathVariable("userId") String userId) {
		service.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
	@PostMapping("/multiple")
	public ResponseEntity<?> addSubscription(@RequestBody MultipleSubscriptionRequest requests) {
		service.addMultipleSubscriptions(requests);
		//subscription requests are modified via subscription details not the vice versa
		//very important
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	

}
