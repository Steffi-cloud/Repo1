package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.Transaction;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repo;

	public Transaction addExpense(Transaction trans) {
		return repo.save(trans);
	}

	public List<Transaction> getListOfTransactionsBasedOffOfDate(LocalDate date) {

		return repo.getListOfTransactionsBasedOffOfDate(date);
	}

	
	public List<Transaction> getListOfTransactionsBasedOfFilter(LocalDate sourceDate,LocalDate targetDate ) {

		return repo.getFilteredTransactionsFilteredByDate(sourceDate,targetDate);
	}
	
	public List<Transaction> getTransactionsByCategoryAndType(Category category,TransactionType type ) {

		return repo.getTransactionsByCategoryAndType(category,type);
	}

}
