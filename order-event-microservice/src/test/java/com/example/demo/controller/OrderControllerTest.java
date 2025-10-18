package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Order;
import com.example.demo.orderservice.CreateOrderProducer;
import com.example.demo.service.OrderController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateOrderProducer createOrderProducer;

    @Test
    void testCreateOrder_Success() throws Exception {
        Order order = new Order();
        order.setOrderID("123");
        order.setContent("Test content");
        order.setDateOfCreation(new Date());

        Mockito.when(createOrderProducer.sendCreateOrderEvent(Mockito.any(Order.class)))
               .thenReturn(true);

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
               .andExpect(status().isOk());
    }
}
