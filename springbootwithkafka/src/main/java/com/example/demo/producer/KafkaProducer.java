package com.example.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppConstants;
@Service
public class KafkaProducer {
	 private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	    @Autowired
	    private KafkaTemplate<String, String> kafkaTemplate;

	    public void sendMessage(String message){
	        LOGGER.info(String.format("Message sent -> %s", message));
	        kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
	    }
}
