package com.example.demo.model;

import java.math.BigDecimal;

public class MonthlySummary {
	private BigDecimal totalIncome;
	private BigDecimal totalExpense;
	private BigDecimal balance;
	public MonthlySummary(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal balance) {
		super();
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
		this.balance = balance;
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
