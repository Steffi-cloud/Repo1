package com.example.demo.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import com.example.demo.repository.MessageRepository;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"myKafkaTest"}, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class MessageConsumerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private MessageRepository messageRepo;
    
    
    public static CountDownLatch latch = new CountDownLatch(1);
    public static String lastMessage = null;

  
    @Test
    void testConsumeMessage() throws Exception {
        Map<String, Object> configs = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaTemplate<String, String> kafkaTemplate =
                new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configs));
        kafkaTemplate.send("myKafkaTest", "test-message");

        // Wait up to 5 seconds for message to be consumed
        boolean messageReceived = MessageConsumer.latch.await(5, TimeUnit.SECONDS);

        // Assert message was received
        assertTrue(messageReceived, "Message was not consumed in time");
        assertEquals("test-message", MessageConsumer.lastMessage);
    }
}
