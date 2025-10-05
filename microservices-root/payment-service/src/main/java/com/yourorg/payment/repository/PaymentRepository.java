
package com.yourorg.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourorg.payment.model.PaymentDetails;

//@Repository

public interface PaymentRepository extends JpaRepository< PaymentDetails,Integer> {

}
