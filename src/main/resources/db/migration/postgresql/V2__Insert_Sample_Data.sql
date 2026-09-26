-- Insert sample customer data
INSERT INTO customer (first_name, last_name, date_of_birth)
VALUES
    ('John', 'Doe', '1990-05-21'),
    ('Jane', 'Smith', '1987-11-14'),
    ('Alice', 'Johnson', '1995-02-03'),
    ('Robert', 'Williams', '1988-07-15'),
    ('Mary', 'Brown', '1992-09-08')
ON CONFLICT DO NOTHING;
