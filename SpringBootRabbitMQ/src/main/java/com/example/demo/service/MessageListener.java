package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "${app.queue}")
    public void receiveMessageForApp1(UserDetails data) {

        log.info("Received message: {}", data);

        try {
            processMessage(data);

        } catch (TransientException e) {
            log.warn("Transient failure, will retry");
            throw new RuntimeException(e); // retry (based on config)

        } catch (Exception e) {
            log.error("Permanent failure, sending to DLQ", e);
            throw new AmqpRejectAndDontRequeueException(e); // DLQ
        }
    }

    private void processMessage(UserDetails data) {
        // Your actual business logic here
    	if (data.getData().equals("fail")) {
    	    throw new RuntimeException("forcing failure");
    	}
        log.info("Processing user: {}", data.getData());
    }
}