package com.example.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;

@Component
public class MessageProducer {
	private Logger log = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private KafkaTemplate<String, Employee> kafkaTemplate;

	
	@Value("${myapp.kafka.topic}")
	private String topic;

	@Value("${myapp.kafka.error-topic}")
	private String errorTopic;

	public void sendMessage(Employee emp) {
		log.info("MESSAGE SENT TO KAFKA: " + emp);
		kafkaTemplate.send(topic, emp); // No string conversion needed
	}

	public void sendError(Employee errorMessage) {
		kafkaTemplate.send(errorTopic, errorMessage);
		log.error("Sent error message: " + errorMessage);
	}

}
