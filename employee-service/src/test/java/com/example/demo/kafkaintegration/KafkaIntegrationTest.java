package com.example.demo.kafkaintegration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEvents;
@SpringBootTest
@EnableKafka
@EmbeddedKafka(partitions = 1, topics = {"employee-event-topic"}, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092", "port=9092"
})
public class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, EmployeeEvents> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Test
    void testSendEmployeeEvent() {
        // Prepare data
        Employee emp = new Employee();
        emp.setId(101);
        emp.setName("IntegrationTest");

        EmployeeEvents event = new EmployeeEvents();
        event.setKeyMap("testEvent");
        event.setEmpList(emp);

        // Send the message
        kafkaTemplate.send("employee-event-topic", event);

        // Set up Kafka consumer to read from topic
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafka);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.demo.model.EmployeeEvents");

        DefaultKafkaConsumerFactory<String, EmployeeEvents> consumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerProps,
                new StringDeserializer(),
                new JsonDeserializer<>(EmployeeEvents.class)
        );

        var consumer = consumerFactory.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "employee-event-topic");

        // Receive record
        ConsumerRecord<String, EmployeeEvents> record = KafkaTestUtils.getSingleRecord(consumer, "employee-event-topic");

        assertThat(record).isNotNull();
        assertThat(record.value().getEmpList().getName()).isEqualTo("IntegrationTest");
        assertThat(record.value().getKeyMap()).isEqualTo("testEvent");

        consumer.close();
    }
}
