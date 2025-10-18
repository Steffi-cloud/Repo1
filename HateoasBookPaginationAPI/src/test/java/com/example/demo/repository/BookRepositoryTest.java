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

import com.example.demo.model.Book;

//import com.example.demo.model.Employee;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	@Autowired

	private BookRepository repo;

	private Book tutorial;

	@BeforeEach
	public void setup() {

		tutorial = new Book();
		tutorial.setTitle("abc");
		tutorial.setAuthor("abc");
		tutorial.setIsbn("abc");
		
		//tutorial.setId(1.0);
	}

	
	@Test
	public void givenBookObject_whenSave_thenReturnSavedBook() {

		// given - precondition or setup
		Book book = new Book();
		book.setTitle("abc");
		book.setAuthor("abc");
		book.setIsbn("abc");
		// the behaviour that we are going test
		Book book1 = repo.save(book);

		// then - verify the output
		assertThat(book1).isNotNull();
		//assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenBookObject_whenFindById_thenReturnBookObject() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Book book = repo.findById(tutorial.getId()).get();

		// then - verify the output
		assertThat(book).isNotNull();
	}
	
	
	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void givenBookList_whenFindAll_thenBookList() {
		// given - precondition or setup

		Book tutorial1 = new Book();

		repo.save(tutorial);
		repo.save(tutorial1);
		ArrayList<Long> idList = new ArrayList<>();
		idList.add(tutorial.getId());
		idList.add(tutorial1.getId());
		// when - action or the behaviour that we are going test
		List<Book> employeeList = repo.findAllById(idList);

		// then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);

	}
	
	@DisplayName("JUnit test for update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		Book savedEmployee = repo.findById(tutorial.getId()).get();
		savedEmployee.setTitle("abc1");
		savedEmployee.setAuthor("abc1");
		savedEmployee.setIsbn("abc1");
		Book updatedEmployee = repo.save(savedEmployee);

		// then - verify the output
		assertThat(updatedEmployee.getTitle()).isEqualTo("abc1");
		assertThat(updatedEmployee.getAuthor()).isEqualTo("abc1");
	}
	@DisplayName("JUnit test for update all employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnAllUpdatedEmployees() {

		
		
	List<Book> tutorialList=	repo.findAll();
	if(tutorialList==null) {
		Book savedEmployee=	repo.save(tutorial);
		savedEmployee.setAuthor("abc1");
		savedEmployee.setIsbn("abc1");
	}
	
	else {
		for(Book tutorials:tutorialList) {
			tutorials.setAuthor("abc1");
			tutorials.setIsbn("abc1");
		}
	}
	
	List<Book> tutorialList1=	repo.findAll();
	for(Book tutorial:tutorialList1) {
		assertThat(tutorial.getAuthor()).isEqualTo("abc1");
		assertThat(tutorial.getIsbn()).isEqualTo("abc1");
	}
	
	
	
	}
	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		repo.save(tutorial);

		// when - action or the behaviour that we are going test
		repo.deleteById(tutorial.getId());
		Optional<Book> employeeOptional = repo.findById(tutorial.getId());

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
