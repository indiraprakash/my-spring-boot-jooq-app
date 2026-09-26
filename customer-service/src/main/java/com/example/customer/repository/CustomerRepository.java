package com.example.customer.repository;

import com.example.customer.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByDateOfBirth(LocalDate dateOfBirth);
}
