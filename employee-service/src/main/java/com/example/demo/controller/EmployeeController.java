package com.example.demo.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEvents;
//import com.example.demo.producer.EmployeeKafkaMessageProducer;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	private Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService service;

	@Autowired
	private KafkaTemplate<String, EmployeeEvents> kafkaTemplate;
	
	/*
	 * @Autowired private EmployeeKafkaMessageProducer producer;
	 */

	@GetMapping("/getEmp")
	public Employee getEmployeeById(@RequestParam Integer id) {

		if (service.getEmployeeById(id) != null) {

			Employee e1 = service.getEmployeeById(id);

			EmployeeEvents event = new EmployeeEvents();
			event.setKeyMap("GET");
			event.setEmpList(e1);
			
			kafkaTemplate.send("employee-event-topic", event);
		}
		return service.getEmployeeById(id);
	}

	@PostMapping("/saveEmp")
	public Employee saveEmployees(@RequestBody Employee emp) {
		
		if (emp != null) {

			
			EmployeeEvents event = new EmployeeEvents();
			
			event.setKeyMap("POST");
			event.setEmpList(emp);
			log.info("sending from service a controller post methood {}",event);
			kafkaTemplate.send("employee-event-topic",event);
			//kafkaTemplate.send("employee-event-topic", event);
		}
		return service.saveEmployees(emp);
	}

}
