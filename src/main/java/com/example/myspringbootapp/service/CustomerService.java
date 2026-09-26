package com.example.myspringbootapp.service;

import com.example.myspringbootapp.model.Customer;
import com.example.myspringbootapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> getCustomersByDateOfBirth(LocalDate dateOfBirth) {
        return customerRepository.findByDateOfBirth(dateOfBirth);
    }
}
