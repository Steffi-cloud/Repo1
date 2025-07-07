package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.Transaction;
import com.example.demo.service.ExpenseService;

@RestController

@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	ExpenseService service;
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	// inside a method

	@PostMapping
	public ResponseEntity<?> saveExpense(@RequestBody Transaction trans) {
		if (trans.getType() == null) {
			return ResponseEntity.badRequest().body("Transaction type must be INCOME or EXPENSE.");
		}

		logger.info("Saving transaction: {}", trans);
		Transaction saved = service.addExpense(trans);
		return ResponseEntity.ok(saved);
	}

	@GetMapping("/transactions/date")
	public List<Transaction> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.info("fetching list of transactions for date: {}", date);

		return service.getListOfTransactionsBasedOffOfDate(date);
	}

	@GetMapping("/transactions/range")
	public List<Transaction> getByDateRange(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sourceDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate) {
		logger.info("fetching list of transactions for sourceDate and targetDate: {}", sourceDate, targetDate);

		return service.getListOfTransactionsBasedOfFilter(sourceDate, targetDate);
	}

	@GetMapping("/transactions/categoryandtype")

	public List<Transaction> getByCategoryAndType(@RequestParam Category category, @RequestParam TransactionType type) {
		logger.info("fetching list of transactions for category and type: {}", category, type);

		return service.getTransactionsByCategoryAndType(category, type);

	}
}
