package com.yourorg.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourorg.payment.model.PaymentDetails;
import com.yourorg.payment.repository.PaymentRepository;
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repo;
	
	public void addPaymentDetails(PaymentDetails details) {
		
		repo.save(details);
	}
}
