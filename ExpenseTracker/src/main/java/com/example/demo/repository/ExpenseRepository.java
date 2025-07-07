package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.Transaction;

public interface ExpenseRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * FROM transaction WHERE date = :targetDate", nativeQuery = true)
	public List<Transaction> getListOfTransactionsBasedOffOfDate(@Param("targetDate") LocalDate date);

	@Query(value = "SELECT * FROM transaction WHERE date BETWEEN :sourceDate AND :targetDate", nativeQuery = true)
	List<Transaction> getFilteredTransactionsFilteredByDate(@Param("sourceDate") LocalDate sourceDate,
			@Param("targetDate") LocalDate targetDate);
	
	@Query(value = "SELECT * FROM transaction WHERE category=:category AND type=:type", nativeQuery = true)
	List<Transaction> getTransactionsByCategoryAndType(@Param("category") Category category,
			@Param("type") TransactionType  type);

}
