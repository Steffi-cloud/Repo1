package com.example.demo.consumer;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Component
@EnableKafka
public class MessageConsumer {
	

	@Autowired
	private EmployeeRepository repo;

	private Logger log = LoggerFactory.getLogger(MessageConsumer.class);

	@KafkaListener(topics = "${myapp.kafka.topic}", groupId = "group1")
	public void consume(Employee employee) {
		log.info("MESSAGE RECEIVED AT CONSUMER END: {}", employee);
		repo.save(employee); // Simple and clean!
	}

	
	@KafkaListener(
		    topics = "error-topic",
		    groupId = "error-group",
		    containerFactory = "stringKafkaListenerContainerFactory"
		)
		public void consume(String message) {
		    System.out.println("Received error message: " + message);
		}

}
