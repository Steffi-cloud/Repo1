package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.producer.MessageProducer;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeBatchService;

@RestController
public class KafkaRestController {
	@Autowired
	private MessageProducer producer;
	private Logger log = LoggerFactory.getLogger(KafkaRestController.class);
	@Autowired
	private EmployeeRepository messageRepo;

	// Send message to kafka
	@PostMapping("/send")
	public String sendMsg(@RequestBody Employee emp) {
		producer.sendMessage(emp);
		return "" + "'+message +'" + " sent successfully!";
	}

	@Autowired
	private KafkaTemplate<String, Employee> stringKafkaTemplate;

	// Read all messages
	@GetMapping("/getAll")
	public List<Employee> getAllMessages() {
		return messageRepo.findAll();
	}

	@GetMapping("/employeesAll")
	public ResponseEntity<Page<Employee>> getEmployees(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> employeesPage = messageRepo.findAll(pageable);

		return ResponseEntity.ok(employeesPage);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> employees = messageRepo.findAll();

		if (employees.size() > 1000) {
			// ❌ Simulate overload
			Employee errorEmployee = new Employee();
			errorEmployee.setId(0);
			errorEmployee.setName("1000 records exceeded");

			stringKafkaTemplate.send("error-topic", errorEmployee);

			// Option 2: Log it and return error
			log.error("Overload: {}", errorEmployee);
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many records to fetch.");
		}

		return ResponseEntity.ok(employees);
	}

	@Autowired
	private EmployeeBatchService batchService;

	@PostMapping("/sendEmployeesToKafka")
	public ResponseEntity<String> sendToKafka() {
		batchService.sendAllEmployeesInChunks();
		return ResponseEntity.ok("All employee chunks sent to Kafka.");
	}

}
