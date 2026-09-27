INSERT INTO customer (first_name, last_name, date_of_birth)
VALUES ('John', 'Doe', '1990-05-21'), ('Jane', 'Smith', '1987-11-14'), ('Alice', 'Johnson', '1995-02-03');

INSERT INTO address (customer_id, street, city, state, postal_code, country)
VALUES (1, '123 Main St', 'New York', 'NY', '10001', 'USA'),
       (2, '456 Oak Ave', 'Los Angeles', 'CA', '90001', 'USA'),
       (3, '789 Pine Rd', 'Chicago', 'IL', '60601', 'USA');
