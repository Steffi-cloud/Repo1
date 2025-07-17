-- Get transactions by category and type
SELECT * FROM transaction WHERE category = 'UPI_TRANSACTIONS_CREDIT' AND type = 'EXPENSE';

-- Get transactions between dates
SELECT * FROM transaction WHERE date BETWEEN '2025-07-01' AND '2025-07-07';

-- Monthly category summary (income and expense)
SELECT 
    category,
    SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
    SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense
FROM transaction
WHERE date BETWEEN '2025-07-01' AND '2025-07-31'
GROUP BY category;
