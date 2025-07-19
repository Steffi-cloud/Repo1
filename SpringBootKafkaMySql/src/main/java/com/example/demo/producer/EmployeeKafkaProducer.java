package com.example.demo.producer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
@Component
public class EmployeeKafkaProducer {
	private static final String TOPIC = "employee-batch-topic";

    @Autowired
    private KafkaTemplate<String, List<Employee>> kafkaTemplate;

    public void sendEmployeeChunk(List<Employee> employeesChunk) {
        kafkaTemplate.send(TOPIC, employeesChunk);
    }
}
