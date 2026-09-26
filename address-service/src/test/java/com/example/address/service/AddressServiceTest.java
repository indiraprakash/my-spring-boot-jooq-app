package com.example.address.service;

import com.example.address.model.Address;
import com.example.address.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    private AddressRepository repository;
    private AddressService service;

    @BeforeEach
    void setUp() {
        repository = mock(AddressRepository.class);
        service = new AddressService(repository);
    }

    @Test
    void returnsAllAddresses() {
        List<Address> addresses = List.of(
                new Address(1L, 1L, "123 Main St", "New York", "NY", "10001", "USA")
        );
        when(repository.findAll()).thenReturn(addresses);

        assertThat(service.getAllAddresses()).containsExactlyElementsOf(addresses);
        verify(repository).findAll();
    }

    @Test
    void returnsAddressesByCustomerId() {
        Long customerId = 1L;
        List<Address> addresses = List.of(
                new Address(1L, customerId, "123 Main St", "New York", "NY", "10001", "USA")
        );
        when(repository.findByCustomerId(customerId)).thenReturn(addresses);

        assertThat(service.getAddressesByCustomerId(customerId)).containsExactlyElementsOf(addresses);
        verify(repository).findByCustomerId(customerId);
    }
}
