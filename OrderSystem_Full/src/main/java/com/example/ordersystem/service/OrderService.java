package com.example.ordersystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ordersystem.model.OrderEntity;
import com.example.ordersystem.payment.PaymentProcessor;
import com.example.ordersystem.repository.OrderRepository;

@Service
public class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final OrderRepository orderRepository;

    public OrderService(PaymentProcessor paymentProcessor, OrderRepository orderRepository) {
        this.paymentProcessor = paymentProcessor;
        this.orderRepository = orderRepository;
    }

    public void processOrder(OrderEntity order) {
        double total = order.getQuantity() * order.getPrice();
        paymentProcessor.pay(total);
        orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
