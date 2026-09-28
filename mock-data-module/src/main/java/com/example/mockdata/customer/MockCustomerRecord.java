package com.example.mockdata.customer;

import java.time.LocalDate;

public record MockCustomerRecord(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) {
}
