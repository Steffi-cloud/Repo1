package com.example.demo.consumer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
@Component

public class EmployeeKafkaConsumer {
	@KafkaListener(topics = "employee-batch-topic", groupId = "employee-batch-group",
		    containerFactory = "employeeBatchKafkaListenerContainerFactory")
    public void processEmployeeChunk(List<Employee> employeesChunk) {
        System.out.println("Received employee chunk of size: " + employeesChunk.size());
        // Process each employee
        for (Employee emp : employeesChunk) {
            // Add your business logic here
            System.out.println("Processing employee: " + emp.getId());
        }
    }
}


