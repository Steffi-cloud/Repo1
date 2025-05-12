package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaProducerService;
@RestController
@RequestMapping(value = "/techgeeknext-kafka/")
public class KafkaExampleController {
	  @Autowired
	    KafkaProducerService kafkaProducer;


	    @GetMapping(value = "/producer")
	    public String sendMessage(@RequestParam("message") String message)
	    {
	        kafkaProducer.send(message);
	        return "Message sent Successfully to the Kafka topic techgeeknext-topic";
	    }
}
