package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.Transaction;
import com.example.demo.repository.ExpenseRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExpenseServiceRemainingTest {
	 private ExpenseRepository expenseRepository;
	    private ExpenseService expenseService;

	    @BeforeEach
	    void setUp() {
	        expenseRepository = mock(ExpenseRepository.class);
	        expenseService = new ExpenseService(expenseRepository);
	    }

	    @Test
	    void testGetFilteredTransactionsByDate() {
	        LocalDate startDate = LocalDate.of(2025, 7, 6);
	        LocalDate endDate = LocalDate.of(2025, 7, 7);

	        Transaction tx1 = new Transaction();
	        tx1.setId(1L);
	        tx1.setAmount(new BigDecimal("500"));
	        tx1.setDate(startDate);
	        tx1.setType(TransactionType.INCOME);
	        tx1.setCategory(Category.CREDIT_CARD_TRANSACTIONS_CREDIT);
	        tx1.setDescription("Test income");
	        Transaction tx2 = new Transaction();
	        tx2.setId(2L);
	        tx2.setAmount(new BigDecimal("10"));
	        tx2.setDate(endDate);
	        tx2.setType(TransactionType.EXPENSE);
	        tx2.setCategory(Category.UPI_TRANSACTIONS_DEBIT);
	        tx2.setDescription("Test income");

	        List<Transaction> mockList = List.of(tx1,tx2);
	        when(expenseRepository.getFilteredTransactionsFilteredByDate(startDate, endDate)).thenReturn(mockList);

	        List<Transaction> result = expenseService.getListOfTransactionsBasedOfFilter(startDate, endDate);

	        assertEquals(2, result.size());
	        assertEquals(tx1.getAmount(), result.get(0).getAmount());
	        verify(expenseRepository).getFilteredTransactionsFilteredByDate(startDate, endDate);

	    }
	    
	    @Test
	    void testGetTransactionsByCategoryAndType() {
	        Category category = Category.FOOD;
	        TransactionType type = TransactionType.EXPENSE;

	        Transaction tx = new Transaction();
	        tx.setId(1L);
	        tx.setAmount(new BigDecimal("250.00"));
	        tx.setCategory(category);
	        tx.setType(type);
	        tx.setDate(LocalDate.now());
	        tx.setDescription("Dinner expense");

	        List<Transaction> mockList = List.of(tx);
	        when(expenseRepository.getTransactionsByCategoryAndTypeNative(category.name(), type.name()))
	                .thenReturn(mockList);

	        List<Transaction> result = expenseService.getTransactionsByCategoryAndType(category, type);

	        assertEquals(1, result.size());
	        assertEquals(category, result.get(0).getCategory());
	        assertEquals(type, result.get(0).getType());
	        verify(expenseRepository).getTransactionsByCategoryAndTypeNative(category.name(), type.name());
	    }
	    
	    @Test
	    void testGetBalanceByDateWithZeroAmountTransactions() {
	        LocalDate startDate = LocalDate.of(2025, 7, 1);
	        LocalDate endDate = LocalDate.of(2025, 7, 31);

	        Transaction zeroIncome = new Transaction();
	        zeroIncome.setAmount(BigDecimal.ZERO);
	        zeroIncome.setType(TransactionType.INCOME);
	        zeroIncome.setCategory(Category.UPI_TRANSACTIONS_CREDIT);
	        zeroIncome.setDate(startDate);

	        Transaction zeroExpense = new Transaction();
	        zeroExpense.setAmount(BigDecimal.ZERO);
	        zeroExpense.setType(TransactionType.EXPENSE);
	        zeroExpense.setCategory(Category.UPI_TRANSACTIONS_DEBIT);
	        zeroExpense.setDate(startDate);

	        List<Transaction> txList = List.of(zeroIncome, zeroExpense);
	        when(expenseRepository.getFilteredTransactionsFilteredByDate(startDate, endDate)).thenReturn(txList);

	        BigDecimal balance = expenseService.getBalanceByDate(startDate, endDate);
	        assertEquals(BigDecimal.ZERO, balance);
	    }

	    @Test
	    void testGetBalanceByDateWithLargeAmounts() {
	        LocalDate startDate = LocalDate.of(2025, 7, 1);
	        LocalDate endDate = LocalDate.of(2025, 7, 31);

	        BigDecimal largeAmount = new BigDecimal("1000000000000"); // 1 trillion

	        Transaction largeIncome = new Transaction();
	        largeIncome.setAmount(largeAmount);
	        largeIncome.setType(TransactionType.INCOME);
	        largeIncome.setCategory(Category.UPI_TRANSACTIONS_CREDIT);
	        largeIncome.setDate(startDate);

	        Transaction largeExpense = new Transaction();
	        largeExpense.setAmount(new BigDecimal("500000000000")); // 500 billion
	        largeExpense.setType(TransactionType.EXPENSE);
	        largeExpense.setCategory(Category.UPI_TRANSACTIONS_DEBIT);
	        largeExpense.setDate(endDate);

	        
	        List<Transaction> txList = List.of(largeIncome, largeExpense);
	        when(expenseRepository.getFilteredTransactionsByDate(startDate, endDate))
	        .thenReturn(txList);


	        BigDecimal expectedBalance = largeIncome.getAmount().subtract(largeExpense.getAmount());
	        
	        System.out.println("amount 1-----"+largeIncome.getAmount());
	        System.out.println("amount 2-----"+largeExpense.getAmount());
	        BigDecimal balance = expenseService.getBalanceByDate(startDate, endDate);
	        System.out.println("balance"+balance);
	        assertEquals(expectedBalance, balance);
	    }


}
