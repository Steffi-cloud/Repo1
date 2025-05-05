package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomRepositoryTest {

	@Autowired
	private CustomerRepository myRepository;

	@Test
	public void testCreateAndFindCustomer() {
		Customer Customer = new Customer();

		Customer.setFirstName("John Doe");
		Customer.setLastName("1234567890");
		Customer.setCustomerId("1234567890");

		myRepository.save(Customer);
		Optional<Customer> foundCustomer = myRepository.findById(Customer.getId());
		assertThat(foundCustomer).isPresent();
		assertThat(foundCustomer.get().getFirstName()).isEqualTo("John Doe");
	}

	@Test
	public void testFindAllCustomers() {
		Customer customer = new Customer();

		customer.setFirstName("John Doe");
		customer.setLastName("1234567890");
		customer.setCustomerId("1234567890");
		Customer customer1 = new Customer();

		customer1.setFirstName("abc");
		customer1.setLastName("1234567890");
		customer1.setCustomerId("1234567891234");

		myRepository.save(customer);
		myRepository.save(customer1);
		List<Customer> foundCustomer = myRepository.findAll();
		assertThat(foundCustomer.size()).isEqualTo(2);

	}

	@Test
	public void testUpdateCustomerById() {

		Optional<Customer> foundCustomer = myRepository.findById((long) 1);
		assertThat(foundCustomer.isEmpty()).isTrue();
		Customer customer = new Customer();

		customer.setFirstName("John Doe");
		customer.setLastName("1234567890");
		customer.setCustomerId("1234567890");
		myRepository.save(customer);
		Customer customer1 = myRepository.findById(customer.getId()).get();
		if (customer1 != null) {
			customer1.setFirstName("abc1");
			customer1.setLastName("abc1");
			customer1.setCustomerId("abc1");
		}

		assertThat(customer1).isNotNull();
		assertThat(customer1.getFirstName()).isEqualTo("abc1");
		assertThat(customer1.getLastName()).isEqualTo("abc1");
		assertThat(customer1.getCustomerId()).isEqualTo("abc1");

	}

	@Test
	public void testDeleteCustomerById() {

		Optional<Customer> foundCustomer = myRepository.findById((long) 1);
		assertThat(foundCustomer.isEmpty()).isTrue();
		Customer customer = new Customer();

		customer.setFirstName("John Doe");
		customer.setLastName("1234567890");
		customer.setCustomerId("1234567890");
		myRepository.save(customer);

		myRepository.count();
		assertThat(myRepository.count()).isEqualTo(1);
		myRepository.deleteById(customer.getId());
		assertThat(myRepository.count()).isEqualTo(0);

	}

	@Test
	public void testDeleteAllCustomer() {

		Optional<Customer> foundCustomer = myRepository.findById((long) 1);
		assertThat(foundCustomer.isEmpty()).isTrue();
		Customer customer = new Customer();

		customer.setFirstName("John Doe");
		customer.setLastName("1234567890");
		customer.setCustomerId("1234567890");
		Customer customer1 = new Customer();

		customer1.setFirstName("John Doe");
		customer1.setLastName("12345678901111");
		customer1.setCustomerId("12345678901111");
		myRepository.save(customer);

		myRepository.save(customer1);

		myRepository.count();
		assertThat(myRepository.count()).isEqualTo(2);
		myRepository.deleteAll();
		assertThat(myRepository.count()).isEqualTo(0);

	}
}
