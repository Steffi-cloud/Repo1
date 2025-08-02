package com.example.ordersystem;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.example.ordersystem.model.OrderEntity;
import com.example.ordersystem.payment.PaymentProcessor;
import com.example.ordersystem.repository.OrderRepository;
import com.example.ordersystem.service.OrderService;

public class OrderServiceTest {

    @Test
    public void testProcessOrder() {
        PaymentProcessor mockProcessor = mock(PaymentProcessor.class);
        OrderRepository mockRepository = mock(OrderRepository.class);

        OrderService orderService = new OrderService(mockProcessor, mockRepository);
        OrderEntity order = new OrderEntity();
        order.setProduct("Laptop");
        order.setQuantity(2);
        order.setPrice(500.0);

        orderService.processOrder(order);

        verify(mockProcessor, times(1)).pay(1000.0);
        verify(mockRepository, times(1)).save(order);
    }
}
