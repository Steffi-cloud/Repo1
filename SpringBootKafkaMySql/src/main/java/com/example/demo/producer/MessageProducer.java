package com.example.demo.producer;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Component
public class MessageProducer {
	private Logger log = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private EmployeeRepository empRepo;

	/*
	 * @Autowired private KafkaTemplate<String, Employee> kafkaTemplate;
	 */

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Value("${myapp.kafka.topic}")
	private String topic;

	public void sendMessage(Employee emp) {
		log.info("MESSAGE SENT FROM PRODUCER END -> " + emp.toString());

		String bodyString = emp.toString();
		
		int s1=bodyString.indexOf('[');
		int s2=bodyString.indexOf(']');
		
		String s11=bodyString.replace(bodyString.charAt(s1), '{');
		
		String s22=s11.replace(s11.charAt(s2), '}');
		log.info("json String",s22);
		
		String passJson=s22.substring(s22.indexOf('{'));
		log.info("passJson",passJson);
		
		String passJson1=passJson.replaceAll("=", ":");
		log.info("passJson1",passJson1);
		
		
		
		kafkaTemplate.send(topic,passJson1);
	}

	
}
