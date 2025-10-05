package com.yourorg.payment.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class SubscriptionRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	@OneToOne(cascade = CascadeType.ALL)
	private SubscriptionDetails subscriptionDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentDetails paymentDetails;

	public SubscriptionDetails getSubscriptionDetails() {
		return subscriptionDetails;
	}
	public void setSubscriptionDetails(SubscriptionDetails subscriptionDetails) {
		this.subscriptionDetails = subscriptionDetails;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
