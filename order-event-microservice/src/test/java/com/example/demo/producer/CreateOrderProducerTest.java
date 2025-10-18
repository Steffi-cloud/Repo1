package com.example.demo.producer;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import com.example.demo.Order;
import com.example.demo.orderservice.CreateOrderProducer;

@ExtendWith(MockitoExtension.class)
class CreateOrderProducerTest {

    @Mock
    private KafkaTemplate<String, Order> kafkaTemplate;

    private CreateOrderProducer producer;

    @BeforeEach
    void setup() {
        producer = new CreateOrderProducer(kafkaTemplate, "test-topic");
    }

    @Test
    void testSendCreateOrderEvent_Success() throws Exception {
        Order order = new Order();
        order.setOrderID("123");
        order.setContent("Test");

        SendResult<String, Order> sendResult = new SendResult<>(null, null);
        CompletableFuture<SendResult<String, Order>> future = CompletableFuture.completedFuture(sendResult);

        Mockito.when(kafkaTemplate.send("test-topic", order)).thenReturn(future);

        boolean result = producer.sendCreateOrderEvent(order);
        Assertions.assertTrue(result);
    }
}
