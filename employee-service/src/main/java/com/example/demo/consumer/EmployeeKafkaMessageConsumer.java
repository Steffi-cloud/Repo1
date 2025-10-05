/*
 * package com.example.demo.consumer;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.kafka.annotation.EnableKafka; import
 * org.springframework.kafka.annotation.KafkaListener; import
 * org.springframework.kafka.core.KafkaTemplate; import
 * org.springframework.stereotype.Component;
 * 
 * import com.example.demo.model.EmployeeEvents;
 * 
 * @Component
 * 
 * @EnableKafka public class EmployeeKafkaMessageConsumer {
 * 
 * @Autowired private KafkaTemplate<String, EmployeeEvents> kafkaTemplate;
 * 
 * @Value("${myapp.kafka.topic}") private String topic;
 * 
 * private Logger log =
 * LoggerFactory.getLogger(EmployeeKafkaMessageConsumer.class);
 * 
 * @KafkaListener(topics = "${myapp.kafka.topic}", groupId = "group1") public
 * void consume(EmployeeEvents employee) {
 * log.info("MESSAGE RECEIVED AT CONSUMER END: {}", employee); // kafkaTemplate.
 * 
 * }
 * 
 * }
 */