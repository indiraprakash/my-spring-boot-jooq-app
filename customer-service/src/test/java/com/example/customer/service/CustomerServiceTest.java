package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository repository;
    private CustomerService service;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        service = new CustomerService(repository);
    }

    @Test
    void returnsAllCustomers() {
        List<Customer> customers = List.of(
                new Customer(1L, "John", "Doe", LocalDate.of(1990, 5, 21))
        );
        when(repository.findAll()).thenReturn(customers);

        assertThat(service.getAllCustomers()).containsExactlyElementsOf(customers);
        verify(repository).findAll();
    }

    @Test
    void returnsCustomerById() {
        Customer customer = new Customer(1L, "John", "Doe", LocalDate.of(1990, 5, 21));
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        assertThat(service.getCustomerById(1L)).contains(customer);
        verify(repository).findById(1L);
    }
}
