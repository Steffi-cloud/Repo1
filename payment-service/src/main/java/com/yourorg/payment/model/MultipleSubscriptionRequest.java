package com.yourorg.payment.model;

import java.util.List;

public class MultipleSubscriptionRequest {
	  private List<SubscriptionRequest> subscriptions;

	public List<SubscriptionRequest> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionRequest> subscriptions) {
		this.subscriptions = subscriptions;
	}


}
