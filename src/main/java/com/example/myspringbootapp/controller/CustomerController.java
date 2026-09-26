package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.model.Customer;
import com.example.myspringbootapp.service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-firstname")
    public List<Customer> searchByFirstName(@RequestParam String firstName) {
        return customerService.getCustomersByFirstName(firstName);
    }

    @GetMapping("/search/by-dob")
    public List<Customer> searchByDateOfBirth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        return customerService.getCustomersByDateOfBirth(dateOfBirth);
    }
}
