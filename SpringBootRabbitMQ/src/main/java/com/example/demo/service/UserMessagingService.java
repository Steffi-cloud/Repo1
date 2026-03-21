package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.config.MessageSender;
import com.example.demo.config.RabbitMQConfig;

@Service
public class UserMessagingService {

    private final MessageSender messageSender;

    public UserMessagingService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendUser(UserDetails user) {
        messageSender.sendMessage(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                user
        );
    }
}