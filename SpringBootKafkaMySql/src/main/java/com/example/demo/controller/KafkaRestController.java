package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.producer.MessageProducer;
import com.example.demo.repository.EmployeeRepository;
@RestController
public class KafkaRestController {
	 @Autowired
     private MessageProducer producer;

     @Autowired
     private EmployeeRepository messageRepo;

     //Send message to kafka
     @PostMapping("/send")
     public String sendMsg(
     @RequestBody Employee emp) {
         producer.sendMessage(emp);
         return "" +"'+message +'" + " sent successfully!";
     }

     //Read all messages
     @GetMapping("/getAll")
     public List<Employee> getAllMessages() {
        return messageRepo.findAll() ;
     }
}
