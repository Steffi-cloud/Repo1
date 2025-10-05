package com.yourorg.subscription.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class SubscriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "subscription_request_id")  // This is the foreign key column
	private MultipleSubscriptionRequest subscriptionRequest;
	private String subscriptionType;

	private String billingCycle;

	private String paymentCycle;

	private boolean valueAddedService;

	public SubscriptionDetails() {

	}

	public SubscriptionDetails(String subscriptionType, String billingCycle, String paymentCycle,
			boolean valueAddedService) {
		super();
		this.subscriptionType = subscriptionType;
		this.billingCycle = billingCycle;
		this.paymentCycle = paymentCycle;
		this.valueAddedService = valueAddedService;
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

	public boolean isValueAddedService() {
		return valueAddedService;
	}

	public void setValueAddedService(boolean valueAddedService) {
		this.valueAddedService = valueAddedService;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billingCycle, paymentCycle, subscriptionType, valueAddedService);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionDetails other = (SubscriptionDetails) obj;
		return Objects.equals(billingCycle, other.billingCycle) && Objects.equals(paymentCycle, other.paymentCycle)
				&& Objects.equals(subscriptionType, other.subscriptionType)
				&& valueAddedService == other.valueAddedService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MultipleSubscriptionRequest getSubscriptionRequest() {
		return subscriptionRequest;
	}

	public void setSubscriptionRequest(MultipleSubscriptionRequest subscriptionRequest) {
		this.subscriptionRequest = subscriptionRequest;
	}

}
