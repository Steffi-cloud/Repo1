
package com.example.demo.kafka.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEvents;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

//@EnableKafka

@Component

public class EmployeeKafkaMessageConsumer {



	IMap<Integer, Employee> employeeCache;
	private final String cacheName = "employeeCache";

	public EmployeeKafkaMessageConsumer(HazelcastInstance hazelcastInstance) {
	  this.employeeCache = hazelcastInstance.getMap(cacheName); //
	 // "productCache" is your Hazelcast map name
	  }

	@KafkaListener(topics = "employee-event-topic", groupId = "employee-group", containerFactory = "kafkaListenerContainerFactory")
	public void consume(EmployeeEvents event) {
		System.out.println("event" + event.getKeyMap());

		String eventType = event.getKeyMap();
		Employee product = event.getEmpList();
		System.out.println("event" + event.getEmpList().getId());
		System.out.println("event" + event.getEmpList().getName());

		switch (eventType) {
		case "POST":
		case "UPDATE":
			employeeCache.put(product.getId(), product);
			System.out.println(
					"Product " + eventType + " event: Added/Updated product with ID " + product.getId() + " in cache.");
			break;
		case "DELETE":
			employeeCache.remove(product.getId());
			System.out.println("Product DELETE event: Removed product with ID " + product.getId() + " from cache.");
			break;
		case "GET":
			// For GET events, you might decide whether to update the cache.
			// Usually, GETs don't change the data, so it might not be necessary to update
			// the cache from this event.
			// However, if you want to ensure a "just accessed" item is in cache, you could
			// put it.
			// This is less common for cache synchronization, but possible.
			if (!employeeCache.containsKey(product.getId())) {
				employeeCache.put(product.getId(), product);
				System.out.println("Product GET event: Put product with ID " + product.getId()
						+ " in cache (if not already present).");
			}
			break;
		default:
			System.out.println("Unknown event type: " + eventType);
			break;
		}

	}

}
