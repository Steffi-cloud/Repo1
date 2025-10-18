package com.yourorg.payment.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourorg.payment.model.PaymentDetails;
import com.yourorg.payment.model.SubscriptionRequest;
//import com.yourorg.payment.model.SubscriptionDetails;
import com.yourorg.payment.repository.PaymentRepository;
import com.yourorg.payment.repository.SubscriptionRepository;

@Service
public class PaymentService {
	private Logger logger = LoggerFactory.getLogger(PaymentService.class);
	@Autowired
	private PaymentRepository repo;
	
	@Autowired
	private SubscriptionRepository subrepo;

	public void addPaymentDetails(PaymentDetails details) {

		repo.save(details);
		logger.info("repo called in addPaymentDetails with {}", details);
	}

	public PaymentDetails getAllSubscriptions(Integer userId) {
		if (repo.existsById(userId)) {

			Optional<PaymentDetails> paymentDetails = repo.findById(userId);
			logger.info("repo called in getAllSubscriptions with {}", userId);
			if (paymentDetails.isPresent()) {
				return paymentDetails.get();
			}
		}
		return null;

	}

	public PaymentDetails updatePaymentMode(Integer userId, PaymentDetails details) {
		if (repo.existsById(userId)) {
			if (details.getId() == null) {
				throw new IllegalArgumentException("PaymentDetails ID must not be null");
			}
//  // Update all relevant fields
			// existingDetails.setPaymentMethod(details.getPaymentMethod());
			// existingDetails.setAmount(details.getAmount());
			// existingDetails.setTransactionId(details.getTransactionId());
			// existingDetails.setTimestamp(details.getTimestamp());
			String payment = details.getPaymentMethod();
			PaymentDetails details1 = repo.findById(details.getId()).get();
			logger.info("repo with userId {}", userId);
			logger.info("repo called in updatePaymentMode with {}", details.getId());
			details1.setPaymentMethod(payment);
			return details1;
		}
		return null;
	}

	public void addSubscriptions(SubscriptionRequest details) {
		// TODO Auto-generated method stub
		
	}

	public void addMultipleSubscriptions(List<SubscriptionRequest> requests) {
		// TODO Auto-generated method stub
		  if (requests == null || requests.isEmpty()) {
	            throw new IllegalArgumentException("Subscription list cannot be empty");
	        }

		  subrepo.saveAll(requests); 
		
	}
}
