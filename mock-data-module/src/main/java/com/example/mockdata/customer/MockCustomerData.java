package com.example.mockdata.customer;

import java.time.LocalDate;
import java.util.List;

public final class MockCustomerData {

    private static final List<MockCustomerRecord> CUSTOMERS = List.of(
            new MockCustomerRecord(1L, "John", "Doe", LocalDate.of(1990, 5, 21)),
            new MockCustomerRecord(2L, "Jane", "Smith", LocalDate.of(1987, 11, 14)),
            new MockCustomerRecord(3L, "Alice", "Johnson", LocalDate.of(1995, 2, 3)),
            new MockCustomerRecord(4L, "Robert", "Williams", LocalDate.of(1988, 7, 15)),
            new MockCustomerRecord(5L, "Mary", "Brown", LocalDate.of(1992, 9, 8))
    );

    private MockCustomerData() {
    }

    public static List<MockCustomerRecord> customers() {
        return CUSTOMERS;
    }
}
