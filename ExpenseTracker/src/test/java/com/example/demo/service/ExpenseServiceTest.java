package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.constants.Category;
import com.example.demo.constants.TransactionType;
import com.example.demo.model.CategorySummary;
import com.example.demo.model.Transaction;
import com.example.demo.repository.ExpenseRepository;

public class ExpenseServiceTest {
	private ExpenseRepository expenseRepository;
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseRepository = mock(ExpenseRepository.class);
        expenseService = new ExpenseService(expenseRepository);
    }
    
    @Test
    void testGetBalanceByDateWithNullDates() {
        assertThrows(NullPointerException.class, () -> {
            expenseService.getBalanceByDate(null, null);
        });
    }

    @Test
    void testGetListOfTransactionsBasedOfFilterWithNullDates() {
        assertThrows(NullPointerException.class, () -> {
            expenseService.getListOfTransactionsBasedOfFilter(null, null);
        });
    }

    @Test
    void testGetMonthlySummaryGroupedByCategoryWithNullDate() {
        assertThrows(NullPointerException.class, () -> {
            expenseService.getMonthlySummaryGroupedByCategory(null);
        });
    }


    @Test
    void testGetBalanceByDate() {
        LocalDate startDate = LocalDate.of(2025, 7, 6);
        LocalDate endDate = LocalDate.of(2025, 7, 7);

        Transaction tx = new Transaction();
        tx.setId(1L);
        tx.setAmount(new BigDecimal("1000"));
        tx.setCategory(Category.UPI_TRANSACTIONS_CREDIT);
        tx.setDate(startDate);
        tx.setDescription("Credit");
        tx.setType(TransactionType.INCOME);
        List<Transaction> mockTransactions=new ArrayList<>();
        mockTransactions.add(tx);
        Transaction expense = new Transaction();
        expense.setId(2L);
        expense.setAmount(new BigDecimal("120"));
        expense.setCategory(Category.UPI_TRANSACTIONS_DEBIT);
        expense.setDate(startDate);
        expense.setDescription("Expense");
        expense.setType(TransactionType.EXPENSE);

        mockTransactions.add(expense);


        when(expenseRepository.getFilteredTransactionsByDate(startDate, endDate))
            .thenReturn(mockTransactions);

        BigDecimal result = expenseService.getBalanceByDate(startDate, endDate);
        assertEquals(new BigDecimal("880"), result);
    }
    @Test
    void testGetBalanceByDateWithSameStartAndEndDate() {
        LocalDate date = LocalDate.of(2025, 7, 7);

        Transaction income = new Transaction();
        income.setAmount(new BigDecimal("200"));
        income.setType(TransactionType.INCOME);
        income.setCategory(Category.UPI_TRANSACTIONS_CREDIT);
        income.setDate(date);

        Transaction expense = new Transaction();
        expense.setAmount(new BigDecimal("50"));
        expense.setType(TransactionType.EXPENSE);
        expense.setCategory(Category.UPI_TRANSACTIONS_DEBIT);
        expense.setDate(date);

        List<Transaction> txList = List.of(income, expense);
        when(expenseRepository.getFilteredTransactionsByDate(date, date))
            .thenReturn(txList);

        BigDecimal expected = new BigDecimal("150");
        BigDecimal result = expenseService.getBalanceByDate(date, date);

        assertEquals(expected, result);
    }
    @Test
    void testGetTransactionsByNullCategoryAndType() {
        List<Transaction> emptyList = Collections.emptyList();

        when(expenseRepository.getTransactionsByCategoryAndTypeNative(null, null))
            .thenReturn(emptyList);

        List<Transaction> result = expenseService.getTransactionsByCategoryAndType(null, null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void testGetTransactionsByCategoryAndType() {
        Category category = Category.ONLINE_PURCHASE;
        TransactionType type = TransactionType.EXPENSE;

        Transaction mockTxn = new Transaction();
        mockTxn.setAmount(BigDecimal.valueOf(250));
        mockTxn.setCategory(category);
        mockTxn.setType(type);

        when(expenseRepository.getTransactionsByCategoryAndTypeNative(category.name(), type.name()))
            .thenReturn(List.of(mockTxn));

        List<Transaction> transactions = expenseService.getTransactionsByCategoryAndType(category, type);

        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        assertEquals(category, transactions.get(0).getCategory());
        assertEquals(type, transactions.get(0).getType());
    }
    
    @Test
    void testGetMonthlySummaryGroupedByCategory() {
        LocalDate endDate = LocalDate.of(2025, 7, 7);
        LocalDate startDate = endDate.minusDays(30);

        // Mock DB result (category, total_income, total_expense)
        List<Object[]> mockResults = List.of(
            new Object[]{"FOOD", new BigDecimal("1500"), new BigDecimal("500")},
            new Object[]{"ELECTRICITY", new BigDecimal("0"), new BigDecimal("200")}
        );

        when(expenseRepository.getMonthlyCategorySummary(startDate, endDate)).thenReturn(mockResults);

        List<CategorySummary> summaries = expenseService.getMonthlySummaryGroupedByCategory(endDate);

        assertEquals(2, summaries.size());

        CategorySummary food = summaries.get(0);
        assertEquals("FOOD", food.getCategory());
        assertEquals(new BigDecimal("1500"), food.getTotalIncome());
        assertEquals(new BigDecimal("500"), food.getTotalExpense());

        CategorySummary electricity = summaries.get(1);
        assertEquals("ELECTRICITY", electricity.getCategory());
        assertEquals(new BigDecimal("0"), electricity.getTotalIncome());
        assertEquals(new BigDecimal("200"), electricity.getTotalExpense());
    }
   

}
