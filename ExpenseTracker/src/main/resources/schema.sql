DROP TABLE IF EXISTS transaction;

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    amount DECIMAL(19,2) NOT NULL,
    type VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    date DATE NOT NULL
);
