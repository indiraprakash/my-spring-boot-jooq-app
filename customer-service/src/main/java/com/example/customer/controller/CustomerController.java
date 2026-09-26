package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

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
