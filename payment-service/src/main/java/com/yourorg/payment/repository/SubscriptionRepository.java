package com.yourorg.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourorg.payment.model.SubscriptionRequest;

public interface SubscriptionRepository extends JpaRepository<SubscriptionRequest, Integer> {

}
