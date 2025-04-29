package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerModel;
import com.example.demo.model.CustomerModelAssembler;
import com.example.demo.service.CustomerService;

/**
 * 
 * https://www.springcloud.io/post/2022-04/hateoas-spring-boot-and-jpa/
 * http://localhost:8080/api/v0/customers
 * 
 * 
 * http://localhost:8080/api/v1/customers?firstNameFilter=&lastNameFilter=
 * 
 * https://stackoverflow.com/questions/48263935/spring-data-page-doesnt-serialize-sort-to-json-correctly
 */
@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private CustomerModelAssembler customerModelAssembler;

	@Autowired
	private PagedResourcesAssembler<Customer> pagedResourcesAssembler;

	/**
	 * @return List of all customers
	 */
	@GetMapping("/api/v0/customers")
	public List<Customer> fetchCustomersAsList() {
		return customerService.fetchCustomerDataAsList();
	}

	@GetMapping("/api/v1/customers")
	public List<Customer> fetchCustomersAsFilteredList(@RequestParam(defaultValue = "")String firstNameFilter,
			@RequestParam(defaultValue = "") String lastNameFilter) {
		return customerService.fetchFilteredCustomerDataAsList(firstNameFilter, lastNameFilter);
	}

	@GetMapping("/api/v2/customers")
	public Page<Customer> fetchCustomersWithPageInterface(@RequestParam(defaultValue = "") String firstNameFilter,
			@RequestParam(defaultValue = "") String lastNameFilter, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "30") int size) {
		return customerService.fetchCustomerDataAsPageWithFiltering(firstNameFilter, lastNameFilter, page, size);
	}
	
	
	@GetMapping("/api/v3/customers")
	public Page<Customer> fetchCustomersWithPageInterfaceAndSorted(@RequestParam(defaultValue = "") String firstNameFilter,
	                                                                @RequestParam(defaultValue = "") String lastNameFilter,
	                                                                @RequestParam(defaultValue = "0") int page,
	                                                                @RequestParam(defaultValue = "30") int size,
	                                                                @RequestParam(defaultValue = "") List<String> sortList,
	                                                                @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
	    return customerService.fetchCustomerDataAsPageWithFilteringAndSorting(firstNameFilter, lastNameFilter, page, size, sortList, sortOrder.toString());
	}
	
	
	@GetMapping("/api/v4/customers")
	public PagedModel<CustomerModel> fetchCustomersWithPagination(
	        @RequestParam(defaultValue = "") String firstNameFilter,
	        @RequestParam(defaultValue = "") String lastNameFilter,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "30") int size,
	        @RequestParam(defaultValue = "") List<String> sortList,
	        @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
	    Page<Customer> customerPage = customerService.fetchCustomerDataAsPageWithFilteringAndSorting(firstNameFilter, lastNameFilter, page, size, sortList, sortOrder.toString());
	    // Use the pagedResourcesAssembler and customerModelAssembler to convert data to PagedModel format
	    return pagedResourcesAssembler.toModel(customerPage, customerModelAssembler);
	}
}
