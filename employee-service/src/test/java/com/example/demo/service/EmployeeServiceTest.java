package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

public class EmployeeServiceTest {
	 @InjectMocks
	    private EmployeeService employeeService;

	    @Mock
	    private EmployeeRepository employeeRepository;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testSaveEmployees() {
	        Employee emp = new Employee(1, "John");
	        when(employeeRepository.save(emp)).thenReturn(emp);

	        Employee result = employeeService.saveEmployees(emp);

	        assertEquals(emp, result);
	        verify(employeeRepository, times(1)).save(emp);
	    }

	    @Test
	    void testGetEmployeeById_Exists() {
	        Employee emp = new Employee(1, "John");
	        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));

	        Employee result = employeeService.getEmployeeById(1);

	        assertNotNull(result);
	        assertEquals("John", result.getName());
	    }

	    @Test
	    void testGetEmployeeById_NotExists() {
	        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

	        Employee result = employeeService.getEmployeeById(1);

	        assertNull(result);
	    }

	    @Test
	    void testGetAllEmployees() {
	        List<Employee> empList = Arrays.asList(
	                new Employee(1, "John"),
	                new Employee(2, "Jane")
	        );
	        when(employeeRepository.findAll()).thenReturn(empList);

	        List<Employee> result = employeeService.getAllEmployees();

	        assertEquals(2, result.size());
	        assertEquals("Jane", result.get(1).getName());
	    }

	    @Test
	    void testUpdateEmployeeById() {
	        Employee existing = new Employee(1, "John");
	        Employee updated = new Employee(1, "John");

	        when(employeeRepository.findById(1)).thenReturn(Optional.of(existing));
	        when(employeeRepository.save(updated)).thenReturn(updated);

	        Employee result = employeeService.updateEmployeeById(updated, 1);

	        assertEquals("John", result.getName());
	      //  assertEquals(70000, result.getSalary());
	    }
}
