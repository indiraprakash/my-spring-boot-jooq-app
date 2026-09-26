package com.example.myspringbootapp.model;

import java.time.LocalDate;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) {
}
