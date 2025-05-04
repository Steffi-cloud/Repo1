package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Employee;

//import com.example.demo.model.Tutorial;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
	@Autowired

	private EmployeeRepository repo;

	private Employee tutorial;

	@BeforeEach
	public void setup() {

		tutorial = new Employee();
		tutorial.setFirstName("abc");
		//tutorial.setId(1.0);
	}

	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

		// given - precondition or setup
		Employee employee = new Employee();
		employee.setFirstName("abc");
		// the behaviour that we are going test
		Employee savedEmployee = repo.save(employee);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();
		//assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Employee employeeDB = repo.findById(tutorial.getId()).get();

		// then - verify the output
		assertThat(employeeDB).isNotNull();
	}
	
	
	
	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void givenEmployeesList_whenFindAll_thenEmployeesList() {
		// given - precondition or setup

		Employee tutorial1 = new Employee();
		tutorial1.setFirstName("abc");

		repo.save(tutorial);
		repo.save(tutorial1);
		ArrayList<Long> idList = new ArrayList<>();
		idList.add(tutorial.getId());
		idList.add(tutorial1.getId());
		// when - action or the behaviour that we are going test
		List<Employee> employeeList = repo.findAllById(idList);

		// then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);

	}
	@DisplayName("JUnit test for update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Employee savedEmployee = repo.findById(tutorial.getId()).get();
		savedEmployee.setFirstName("ram@gmail.com");
		savedEmployee.setLastName("Ram");
		Employee updatedEmployee = repo.save(savedEmployee);

		// then - verify the output
		assertThat(updatedEmployee.getFirstName()).isEqualTo("ram@gmail.com");
		assertThat(updatedEmployee.getLastName()).isEqualTo("Ram");
	}
	@DisplayName("JUnit test for update all employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnAllUpdatedEmployees() {

		
		
	List<Employee> tutorialList=	repo.findAll();
	if(tutorialList==null) {
		Employee savedEmployee=	repo.save(tutorial);
		savedEmployee.setFirstName("abc");
		savedEmployee.setLastName("abc");
	}
	
	else {
		for(Employee tutorials:tutorialList) {
			tutorials.setFirstName("abc");
			tutorials.setLastName("abc");
		}
	}
	
	List<Employee> tutorialList1=	repo.findAll();
	for(Employee tutorial:tutorialList1) {
		assertThat(tutorial.getFirstName()).isEqualTo("abc");
		assertThat(tutorial.getLastName()).isEqualTo("abc");
	}
	
	
	
	}
	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		repo.deleteById(tutorial.getId());
		Optional<Employee> employeeOptional = repo.findById(tutorial.getId());

		// then - verify the output
		assertThat(employeeOptional).isEmpty();
	}
	@DisplayName("JUnit test for delete all employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveAllEmployee() {

		//repo.save(tutorial);

		// when - action or the behaviour that we are going test
		repo.deleteAll();
		//Optional<Tutorial> employeeOptional = repo.findById(tutorial.getId());

		// then - verify the output
		assertThat(repo.count()).isEqualTo(0);
	}
}
