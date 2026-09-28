package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
