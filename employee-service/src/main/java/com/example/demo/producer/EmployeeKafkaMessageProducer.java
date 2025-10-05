/*
 * package com.example.demo.producer;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.kafka.core.KafkaTemplate; import
 * org.springframework.stereotype.Component;
 * 
 * import com.example.demo.model.EmployeeEvents;
 * 
 * @Component public class EmployeeKafkaMessageProducer { private Logger log =
 * LoggerFactory.getLogger(EmployeeKafkaMessageProducer.class);
 * 
 * @Autowired private KafkaTemplate<String, EmployeeEvents> kafkaTemplate;
 * 
 * @Value("${myapp.kafka.topic}") private String topic;
 * 
 * public void sendMessage(EmployeeEvents emp) {
 * log.info("MESSAGE SENT TO KAFKA  IN MESSAGE PRODUCER: " + emp);
 * kafkaTemplate.send(topic, emp); }
 * 
 * }
 */