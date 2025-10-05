package com.yourorg.subscription.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SubscriptionRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_details_id")
	private SubscriptionDetails subscriptionDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_details_id")
	private PaymentDetails paymentDetails;
	
	public SubscriptionRequest() {
		
	}
	public SubscriptionRequest(String userId, SubscriptionDetails subscriptionDetails, PaymentDetails paymentDetails) {
		super();
		this.userId = userId;
		this.subscriptionDetails = subscriptionDetails;
		this.paymentDetails = paymentDetails;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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

}
