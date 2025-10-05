package com.yourorg.subscription.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String paymentMethod;

	private int amount;

	private String transactionId;

	private String timeStamp;

	@OneToOne
	@JoinColumn(name = "subscription_request_id")
	private MultipleSubscriptionRequest subscriptionRequest;

	public PaymentDetails() {

	}

	public PaymentDetails(String paymentMethod, int amount, String transactionId, String timeStamp) {
		super();
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.transactionId = transactionId;
		this.timeStamp = timeStamp;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, paymentMethod, timeStamp, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentDetails other = (PaymentDetails) obj;
		return amount == other.amount && Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(timeStamp, other.timeStamp) && Objects.equals(transactionId, other.transactionId);
	}
}
