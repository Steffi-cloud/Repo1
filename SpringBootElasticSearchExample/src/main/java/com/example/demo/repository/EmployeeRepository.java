package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String>{

}
