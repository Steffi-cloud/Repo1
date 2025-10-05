package com.yourorg.payment.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	   @OneToOne(cascade = CascadeType.ALL)
	private SubscriptionDetails details;
	
	   @OneToOne(cascade = CascadeType.ALL)
	private PaymentDetails paymentDetails;


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public SubscriptionDetails getDetails() {
		return details;
	}


	public void setDetails(SubscriptionDetails details) {
		this.details = details;
	}


	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}


	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

}
