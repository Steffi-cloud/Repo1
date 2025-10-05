package com.yourorg.subscription.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class MultipleSubscriptionRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	@OneToMany(mappedBy = "subscriptionRequest", cascade = CascadeType.ALL)

	private List<SubscriptionDetails> subscriptions;

	@OneToOne(mappedBy = "subscriptionRequest", cascade = CascadeType.ALL)
	private PaymentDetails paymentDetails;

	public MultipleSubscriptionRequest() {

	}

	public MultipleSubscriptionRequest(String userId, List<SubscriptionDetails> subscriptions,
			PaymentDetails paymentDetails) {
		super();
		this.userId = userId;
		this.subscriptions = subscriptions;
		this.paymentDetails = paymentDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SubscriptionDetails> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDetails> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentDetails, subscriptions, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipleSubscriptionRequest other = (MultipleSubscriptionRequest) obj;
		return Objects.equals(paymentDetails, other.paymentDetails)
				&& Objects.equals(subscriptions, other.subscriptions) && Objects.equals(userId, other.userId);
	}

}
