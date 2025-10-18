package com.example.demo.consumer;

import com.example.demo.consumer.KafkaConsumer;

import org.junit.jupiter.api.Test;

public class KafkaConsumerTest {

	 @Test
	    public void testConsume() throws Exception {
	        KafkaConsumer consumer = new KafkaConsumer();
	        String message = "Hello Kafka!";

	        // Call the consume method directly
	        consumer.consume(message);
	        
	        // You can manually inspect logs or use LogCaptor for a more robust test
	    }
}
