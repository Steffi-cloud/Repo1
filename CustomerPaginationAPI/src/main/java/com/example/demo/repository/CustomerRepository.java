package com.example.demo.repository;

import java.util.List;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	String FILTER_CUSTOMERS_ON_FIRST_NAME_AND_LAST_NAME_QUERY = "select b from Customer b where UPPER(b.firstName) like CONCAT('%',UPPER(?1),'%') and UPPER(b.lastName) like CONCAT('%',UPPER(?2),'%')";

	@Query(FILTER_CUSTOMERS_ON_FIRST_NAME_AND_LAST_NAME_QUERY)
	List<Customer> findByFirstNameLikeAndLastNameLike(String firstNameFilter, String lastNameFilter);
	
	
	
	@Query(FILTER_CUSTOMERS_ON_FIRST_NAME_AND_LAST_NAME_QUERY)
	Page<Customer> findByFirstNameLikeAndLastNameLike(String firstNameFilter, String lastNameFilter, PageRequest pageable);

}
