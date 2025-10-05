
package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.hazelcast.core.HazelcastInstance;

@Service
public class CacheService {

	@Autowired
	private HazelcastInstance hazelcastInstance;

	private final String CACHE_NAME = "employeeCache";

	public void put(int id, Employee employee) {
		hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).put(id, employee);
	}

	public Employee get(int id) {
		//;
		System.out
				.println("lets see cache response " + hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).get(id));
		return hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).get(id);
	}

	public void clear() {
		hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).clear();
	}

	public int size() {
		return hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).size();
	}

	public Collection<Employee> getAll() {
		return hazelcastInstance.<Integer, Employee>getMap(CACHE_NAME).values();
	}
}
