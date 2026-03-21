package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.UserDetails;
import com.example.demo.service.UserMessagingService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMessagingService userMessagingService;

    public UserController(UserMessagingService userMessagingService) {
        this.userMessagingService = userMessagingService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendUser(@RequestBody UserDetails user) {

        userMessagingService.sendUser(user);

        return ResponseEntity.ok("Message sent to RabbitMQ");
    }
}