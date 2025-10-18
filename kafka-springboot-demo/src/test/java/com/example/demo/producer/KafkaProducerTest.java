package com.example.demo.producer;

import com.example.demo.config.AppConstants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

public class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;

    public KafkaProducerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {
        String message = "Test Message";
        kafkaProducer.sendMessage(message);

        verify(kafkaTemplate).send(AppConstants.TOPIC_NAME, message);
    }
}
