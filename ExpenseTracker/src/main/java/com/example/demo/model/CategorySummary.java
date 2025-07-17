package com.example.demo.model;

import java.math.BigDecimal;

public class CategorySummary {
	private String category;
	private BigDecimal totalIncome;
	private BigDecimal totalExpense;

	public CategorySummary(String category, BigDecimal totalIncome, BigDecimal totalExpense) {
		super();
		this.category = category;
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}
}
