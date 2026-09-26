CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL
);

INSERT INTO customer (first_name, last_name, date_of_birth)
VALUES
    ('John', 'Doe', '1990-05-21'),
    ('Jane', 'Smith', '1987-11-14'),
    ('Alice', 'Johnson', '1995-02-03');
