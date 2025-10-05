package com.yourorg.subscription.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yourorg.subscription.model.MultipleSubscriptionRequest;
import com.yourorg.subscription.model.PaymentDetails;
import com.yourorg.subscription.model.SubscriptionDetails;
import com.yourorg.subscription.model.SubscriptionRequest;
import com.yourorg.subscription.model.SubscriptionUpdateRequest;

@Service
public class SubscriptionService {
	List<SubscriptionRequest> requests = new ArrayList<>();

	public List<SubscriptionRequest> addCache() {
		requests.add(new SubscriptionRequest("1", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("NEFT", 1000, "1234567890", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("2", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("DEBITCARD", 1000, "3436475658578", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("3", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("UPI", 1000, "324652572", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("4", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("CREDITCARD", 1000, "679576489480", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("5", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("NEFT", 1000, "4376737944408", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("6", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("DEBITCARD", 1000, "4478440848484", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("7", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("UPI", 1000, "857674958580", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("8", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("CREDITCARD", 1000, "12345621316431", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("9", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("NEFT", 1000, "764794868408", "2025-09-22T13:11:00")));
		requests.add(new SubscriptionRequest("10", new SubscriptionDetails("monthly", "monthly", "monthly", false),
				new PaymentDetails("DEBITCARD", 1000, "12643175157572", "2025-09-22T13:11:00")));

		return requests;

	}

	public void addSubscription(SubscriptionRequest request) {
		requests.add(request);

	}

	public void updateRequest(String userId, SubscriptionUpdateRequest reqs) {
		for (SubscriptionRequest req : requests) {
			if (req.getUserId().equalsIgnoreCase(userId)) {
				SubscriptionDetails details = req.getSubscriptionDetails();

				if (!isBlank(reqs.getBillingCycle())) {
					details.setBillingCycle(reqs.getBillingCycle());
				}

				if (!isBlank(reqs.getPaymentCycle())) {
					details.setPaymentCycle(reqs.getPaymentCycle());
				}

				if (!isBlank(reqs.getSubscriptionType())) {
					details.setSubscriptionType(reqs.getSubscriptionType());
				}
			}
		}
	}

	private boolean isBlank(String str) {
		return str == null || str.isBlank();
	}

	public void deleteUser(String userId) {
		for (SubscriptionRequest req : requests) {
			if (req.getUserId().equalsIgnoreCase(userId)) {
				requests.remove(userId);

			}
		}
	}

	public void addMultipleSubscriptions(MultipleSubscriptionRequest request) {
		//subscription requests are modified via subscription details not the vice versa
				//very important
		for (SubscriptionRequest req : requests) {
			if (req.getSubscriptionDetails().equals(request)) {

				continue;
			}

			else {
				List<SubscriptionDetails> subs=request.getSubscriptions();
				for(SubscriptionDetails details:subs) {
					req.setSubscriptionDetails(details);
				}
				
			
			}
		}

	}

}
