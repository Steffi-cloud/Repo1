package com.example.ordersystem.payment;

import org.springframework.stereotype.Component;

@Component
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
