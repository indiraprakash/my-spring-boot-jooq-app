CREATE TABLE IF NOT EXISTS address (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customer(id),
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(50),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_address_customer_id ON address(customer_id);
CREATE INDEX IF NOT EXISTS idx_address_city ON address(city);
