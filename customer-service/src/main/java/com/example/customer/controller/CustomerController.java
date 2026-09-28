package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        log.info("GET /api/customers - retrieving all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        log.info("GET /api/customers/{} - retrieving customer by id", id);
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-firstname")
    public List<Customer> searchByFirstName(@RequestParam String firstName) {
        log.info("GET /api/customers/search/by-firstname?firstName={} - searching by first name", firstName);
        return customerService.getCustomersByFirstName(firstName);
    }

    @GetMapping("/search/by-dob")
    public List<Customer> searchByDateOfBirth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        log.info("GET /api/customers/search/by-dob?dateOfBirth={} - searching by date of birth", dateOfBirth);
        return customerService.getCustomersByDateOfBirth(dateOfBirth);
    }
}
