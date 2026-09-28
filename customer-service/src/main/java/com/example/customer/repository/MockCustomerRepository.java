package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev")
public class MockCustomerRepository implements CustomerRepository {

    private static final Logger log = LoggerFactory.getLogger(MockCustomerRepository.class);

    private static final List<Customer> CUSTOMERS = List.of(
            new Customer(1L, "John", "Doe", LocalDate.of(1990, 5, 21)),
            new Customer(2L, "Jane", "Smith", LocalDate.of(1987, 11, 14)),
            new Customer(3L, "Alice", "Johnson", LocalDate.of(1995, 2, 3)),
            new Customer(4L, "Robert", "Williams", LocalDate.of(1988, 7, 15)),
            new Customer(5L, "Mary", "Brown", LocalDate.of(1992, 9, 8))
    );

    @Override
    public Optional<Customer> findById(Long id) {
        log.debug("Mock repository: findById({})", id);
        return CUSTOMERS.stream()
                .filter(customer -> customer.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Mock repository: findAll()");
        return CUSTOMERS;
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        log.debug("Mock repository: findByFirstName({})", firstName);
        return CUSTOMERS.stream()
                .filter(customer -> customer.firstName().equalsIgnoreCase(firstName))
                .toList();
    }

    @Override
    public List<Customer> findByDateOfBirth(LocalDate dateOfBirth) {
        log.debug("Mock repository: findByDateOfBirth({})", dateOfBirth);
        return CUSTOMERS.stream()
                .filter(customer -> customer.dateOfBirth().equals(dateOfBirth))
                .toList();
    }
}
