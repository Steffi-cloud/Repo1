package com.yourorg.subscription.model;

public class SubscriptionUpdateRequest {
	
	private String subscriptionType;
	
	private String billingCycle;
	
	private String paymentCycle;
	
	public SubscriptionUpdateRequest() {
		
	}

	public SubscriptionUpdateRequest(String subscriptionType, String billingCycle, String paymentCycle) {
		super();
		this.subscriptionType = subscriptionType;
		this.billingCycle = billingCycle;
		this.paymentCycle = paymentCycle;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

}
