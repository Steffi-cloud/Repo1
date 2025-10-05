
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kafka.config.EmployeeKafkaMessageConsumer;
import com.example.demo.model.Employee;
import com.example.demo.service.CacheService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@RestController

@RequestMapping("/cache")
public class CacheController {
	@Autowired
	private CacheService cacheService;

	@Autowired
	private EmployeeKafkaMessageConsumer consumer;

	private Logger log = LoggerFactory.getLogger(CacheController.class);

	private final IMap<String, Employee> productCache;

	public CacheController(HazelcastInstance hazelcastInstance) {
		// this.cacheService = new CacheService();
		this.productCache = hazelcastInstance.getMap("employeeCache");
	}

	@PostMapping("/cachePost")
	public void put(@RequestParam String key, @RequestParam String value) {

		cacheService.put(Integer.parseInt(key), new Employee(Integer.parseInt(key), value));
		log.info("putting key and values in cache: {}, {}", key, value);
	}
	
	
	 @GetMapping("/{id}")
	    public Employee getProductFromCache(@PathVariable String id) {
	        return productCache.get(Integer.parseInt(id)); // Retrieve directly from cache
	    }

	
}
