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


import com.example.demo.model.Product;

//import com.example.demo.model.Book;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
	@Autowired

	private ProductRepository repo;

	private Product product;

	@BeforeEach
	public void setup() {

		product = new Product();
		product.setName("abc");
		product.setPrice(10);

		// tutorial.setId(1.0);
	}

	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

		// given - precondition or setup
		Product employee = new Product();
		product.setName("abc");
		product.setPrice(10);
		// the behaviour that we are going test
		Product savedEmployee = repo.save(employee);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();
		// assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

		repo.save(product);

		// when - action or the behaviour that we are going test
		Product product1 = repo.findById(product.getId()).get();

		// then - verify the output
		assertThat(product1).isNotNull();
	}

	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void givenEmployeesList_whenFindAll_thenEmployeesList() {
		// given - precondition or setup

		Product tutorial1 = new Product();
		product.setName("abc");
		product.setPrice(10);

		repo.save(product);
		repo.save(tutorial1);
		ArrayList<Long> idList = new ArrayList<>();
		idList.add(product.getId());
		idList.add(tutorial1.getId());
		// when - action or the behaviour that we are going test
		List<Product> employeeList = repo.findAllById(idList);

		// then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);

	}

	@DisplayName("JUnit test for update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		repo.save(product);

		// when - action or the behaviour that we are going test
		Product savedEmployee = repo.findById(product.getId()).get();
		product.setName("abc");
		product.setPrice(10);

		Product updatedEmployee = repo.save(savedEmployee);

		// then - verify the output
		assertThat(updatedEmployee.getName()).isEqualTo("abc");
		assertThat(updatedEmployee.getPrice()).isEqualTo(10);
	}

	@DisplayName("JUnit test for update all employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnAllUpdatedEmployees() {

		List<Product> tutorialList = repo.findAll();
		if (tutorialList == null) {
			Product savedEmployee = repo.save(product);
			savedEmployee.setName("abc");
			savedEmployee.setPrice(10);
		}

		else {
			for (Product tutorials : tutorialList) {
				tutorials.setName("abc");
				tutorials.setPrice(10);
			}
		}

	}
	
	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		repo.save(product);

		// when - action or the behaviour that we are going test
		repo.deleteById(product.getId());
		Optional<Product> employeeOptional = repo.findById(product.getId());

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
