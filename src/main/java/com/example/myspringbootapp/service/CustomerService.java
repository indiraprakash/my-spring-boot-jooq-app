package com.example.myspringbootapp.service;

import com.example.myspringbootapp.model.Customer;
import com.example.myspringbootapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        log.debug("Fetching all customers");
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        log.debug("Fetching customer by id: {}", id);
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        log.debug("Searching customers by firstName: {}", firstName);
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> getCustomersByDateOfBirth(LocalDate dateOfBirth) {
        log.debug("Searching customers by dateOfBirth: {}", dateOfBirth);
        return customerRepository.findByDateOfBirth(dateOfBirth);
    }
}
