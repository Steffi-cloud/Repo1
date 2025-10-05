package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired

	private EmployeeRepository repo;

	public Employee saveEmployees(Employee emp) {
		return repo.save(emp);
	}

	public Employee getEmployeeById(Integer id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		}
		return null;
	}

	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	public Employee updateEmployeeById(Employee emp, Integer id) {
		if (repo.findById(id).isPresent()) {
			return repo.save(emp);
		}

		return repo.save(emp);
	}

}
