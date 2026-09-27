CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_customer_first_name ON customer(first_name);
CREATE INDEX IF NOT EXISTS idx_customer_dob ON customer(date_of_birth);
