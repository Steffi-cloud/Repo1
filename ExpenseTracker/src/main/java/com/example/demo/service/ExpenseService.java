package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.CategorySummary;
import com.example.demo.model.MonthlySummary;
import com.example.demo.model.Transaction;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseService {

	private ExpenseRepository repo;

	@Autowired
	public ExpenseService(ExpenseRepository expenseRepository) {
		this.repo = expenseRepository;
	}

	public Transaction addExpense(Transaction trans) {
		return repo.save(trans);
	}

	public List<Transaction> getListOfTransactionsBasedOffOfDate(LocalDate date) {
		if (date == null ) {
		    throw new NullPointerException("Dates must not be null");
		}

		return repo.getListOfTransactionsBasedOffOfDate(date);
	}

	public List<Transaction> getListOfTransactionsBasedOfFilter(LocalDate sourceDate, LocalDate targetDate) {
		if (sourceDate == null || targetDate == null) {
		    throw new NullPointerException("Dates must not be null");
		}

		return repo.getFilteredTransactionsFilteredByDate(sourceDate, targetDate);
	}

	public List<Transaction> getTransactionsByCategoryAndType(Category category, TransactionType type) {
		 if (category == null || type == null) {
		        return Collections.emptyList();
		    }
		return repo.getTransactionsByCategoryAndTypeNative(category.name(), type.name());
	}

	public BigDecimal getBalanceByDate(LocalDate date0, LocalDate date1) {
		if (date0 == null || date1 == null) {
		    throw new NullPointerException("Dates must not be null");
		}

		List<Transaction> transactions = repo.getFilteredTransactionsByDate(date0, date1);

		BigDecimal income = transactions.stream().filter(t -> t.getType() == TransactionType.INCOME)
				.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal expense = transactions.stream().filter(t -> t.getType() == TransactionType.EXPENSE)
				.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

		return income.subtract(expense); // or return BigDecimal
	}

	public MonthlySummary getMonthlySummary(LocalDate endDate) {
		if (endDate == null) {
		    throw new NullPointerException("Dates must not be null");
		}

		LocalDate startDate = endDate.minusDays(30);
		List<Transaction> transactions = repo.findTransactionsByDateRange(startDate, endDate);

		BigDecimal totalIncome = transactions.stream().filter(t -> t.getType() == TransactionType.INCOME)
				.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal totalExpense = transactions.stream().filter(t -> t.getType() == TransactionType.EXPENSE)
				.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal balance = totalIncome.subtract(totalExpense);

		return new MonthlySummary(totalIncome, totalExpense, balance);
	}

	public List<CategorySummary> getMonthlySummaryGroupedByCategory(LocalDate endDate) {
		LocalDate startDate = endDate.minusDays(30);
		List<Object[]> results = repo.getMonthlyCategorySummary(startDate, endDate);

		return results.stream().map(row -> new CategorySummary(row[0].toString(), new BigDecimal(row[1].toString()),
				new BigDecimal(row[2].toString()))).collect(Collectors.toList());
	}

}
