package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Transaction;

public interface ExpenseRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * FROM transaction WHERE date = :targetDate", nativeQuery = true)
	public List<Transaction> getListOfTransactionsBasedOffOfDate(@Param("targetDate") LocalDate date);

	@Query(value = "SELECT * FROM transaction WHERE date BETWEEN :sourceDate AND :targetDate", nativeQuery = true)
	List<Transaction> getFilteredTransactionsFilteredByDate(@Param("sourceDate") LocalDate sourceDate,
			@Param("targetDate") LocalDate targetDate);

	@Query(value = "SELECT * FROM transaction WHERE category = :category AND type = :type", nativeQuery = true)
	List<Transaction> getTransactionsByCategoryAndTypeNative(@Param("category") String category,
			@Param("type") String type);

	@Query(value = "SELECT * FROM transaction WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<Transaction> getFilteredTransactionsByDate(@Param("startDate") LocalDate date0, @Param("endDate") LocalDate date1);

	
	@Query(value = "SELECT * FROM transaction WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<Transaction> findTransactionsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(value = """
		    SELECT 
		        category,
		        SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
		        SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense
		    FROM transaction
		    WHERE date BETWEEN :startDate AND :endDate
		    GROUP BY category
		""", nativeQuery = true)
		List<Object[]> getMonthlyCategorySummary(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
