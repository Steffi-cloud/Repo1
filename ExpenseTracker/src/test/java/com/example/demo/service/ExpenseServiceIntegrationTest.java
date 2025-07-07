package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.CategorySummary;
@SpringBootTest
public class ExpenseServiceIntegrationTest {
    @Autowired
    private ExpenseService expenseService;

    @Test
    void testGetMonthlySummaryGroupedByCategory() {
        LocalDate date = LocalDate.of(2025, 7, 7); // date within range of your test data

        List<CategorySummary> summary = expenseService.getMonthlySummaryGroupedByCategory(date);

        assertNotNull(summary);
        assertFalse(summary.isEmpty());

        boolean foundFood = summary.stream().anyMatch(s -> s.getCategory().equals("FOOD"));
        assertTrue(foundFood);

        summary.forEach(s -> {
            assertNotNull(s.getCategory());
            assertNotNull(s.getTotalExpense());
            assertNotNull(s.getTotalIncome());
        });
    }
}
