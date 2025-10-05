package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.producer.EmployeeKafkaProducer;
import com.example.demo.repository.EmployeeRepository;
@Service
public class EmployeeBatchService {
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeKafkaProducer kafkaProducer;

    public void sendAllEmployeesInChunks() {
        int page = 0;
        int size = 1000;
        Page<Employee> employeePage;

        do {
            Pageable pageable = PageRequest.of(page, size);
            employeePage = employeeRepository.findAll(pageable);
            List<Employee> chunk = employeePage.getContent();

            kafkaProducer.sendEmployeeChunk(chunk);
            System.out.println("Sent chunk of size: " + chunk.size());

            page++;
        } while (!employeePage.isLast());
   }
}
