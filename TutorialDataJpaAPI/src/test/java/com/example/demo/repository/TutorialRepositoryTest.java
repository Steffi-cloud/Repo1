
package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.example.demo.model.Tutorial;

//junit 4
//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TutorialRepositoryTest {

	@Autowired

	private TutorialRepository repo;

	private Tutorial tutorial;

	@BeforeEach
	public void setup() {

		tutorial = new Tutorial("Ramesh", "Fadatare", true);
	}

	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

		// given - precondition or setup
		Tutorial employee = new Tutorial("Ramesh", "Fadatare", true);
		// the behaviour that we are going test
		Tutorial savedEmployee = repo.save(employee);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Tutorial employeeDB = repo.findById(tutorial.getId()).get();

		// then - verify the output
		assertThat(employeeDB).isNotNull();
	}

	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void givenEmployeesList_whenFindAll_thenEmployeesList() {
		// given - precondition or setup

		Tutorial tutorial1 = new Tutorial("abc", "abc", true);

		repo.save(tutorial);
		repo.save(tutorial1);
		ArrayList<Long> idList = new ArrayList<>();
		idList.add(tutorial.getId());
		idList.add(tutorial1.getId());
		// when - action or the behaviour that we are going test
		List<Tutorial> employeeList = repo.findAllById(idList);

		// then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);

	}

	@DisplayName("JUnit test for update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Tutorial savedEmployee = repo.findById(tutorial.getId()).get();
		savedEmployee.setTitle("ram@gmail.com");
		savedEmployee.setDescription("Ram");
		Tutorial updatedEmployee = repo.save(savedEmployee);

		// then - verify the output
		assertThat(updatedEmployee.getTitle()).isEqualTo("ram@gmail.com");
		assertThat(updatedEmployee.getDescription()).isEqualTo("Ram");
	}

	@DisplayName("JUnit test for update all employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnAllUpdatedEmployees() {

		
		
	List<Tutorial> tutorialList=	repo.findAll();
	if(tutorialList==null) {
		Tutorial savedEmployee=	repo.save(tutorial);
		savedEmployee.setDescription("abc");
		savedEmployee.setTitle("abc");
	}
	
	else {
		for(Tutorial tutorials:tutorialList) {
			tutorials.setDescription("abc");
			tutorials.setTitle("abc");
		}
	}
	
	List<Tutorial> tutorialList1=	repo.findAll();
	for(Tutorial tutorial:tutorialList1) {
		assertThat(tutorial.getDescription()).isEqualTo("abc");
		assertThat(tutorial.getTitle()).isEqualTo("abc");
	}
	
	
	
	}
	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		repo.deleteById(tutorial.getId());
		Optional<Tutorial> employeeOptional = repo.findById(tutorial.getId());

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
