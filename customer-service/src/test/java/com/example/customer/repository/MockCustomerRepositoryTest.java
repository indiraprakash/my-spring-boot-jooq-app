package com.example.customer.repository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MockCustomerRepositoryTest {

    private final MockCustomerRepository repository = new MockCustomerRepository();

    @Test
    void findsCustomersUsingRecordAccessors() {
        assertThat(repository.findById(1L))
                .get()
                .satisfies(customer -> {
                    assertThat(customer.id()).isEqualTo(1L);
                    assertThat(customer.firstName()).isEqualTo("John");
                    assertThat(customer.lastName()).isEqualTo("Doe");
                    assertThat(customer.dateOfBirth()).isEqualTo(LocalDate.of(1990, 5, 21));
                });
    }

    @Test
    void filtersCustomersByFirstNameAndDateOfBirth() {
        assertThat(repository.findByFirstName("jane"))
                .singleElement()
                .satisfies(customer -> assertThat(customer.id()).isEqualTo(2L));

        assertThat(repository.findByDateOfBirth(LocalDate.of(1995, 2, 3)))
                .singleElement()
                .satisfies(customer -> assertThat(customer.firstName()).isEqualTo("Alice"));
    }
}
