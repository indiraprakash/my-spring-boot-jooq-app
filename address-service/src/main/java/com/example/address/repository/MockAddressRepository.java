package com.example.address.repository;

import com.example.address.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("dev")
@Slf4j
public class MockAddressRepository implements AddressRepository {

    private static final List<Address> ADDRESSES = new ArrayList<>(List.of(
            new Address(1L, 1L, "123 Main St", "New York", "NY", "10001", "USA"),
            new Address(2L, 2L, "456 Oak Ave", "Los Angeles", "CA", "90001", "USA"),
            new Address(3L, 3L, "789 Pine Rd", "Chicago", "IL", "60601", "USA")
    ));

    private static final AtomicLong ID_GENERATOR = new AtomicLong(3);

    @Override
    public Optional<Address> findById(Long id) {
        log.debug("Mock repository: findById({})", id);
        return ADDRESSES.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Address> findAll() {
        log.debug("Mock repository: findAll()");
        return new ArrayList<>(ADDRESSES);
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        log.debug("Mock repository: findByCustomerId({})", customerId);
        return ADDRESSES.stream()
                .filter(address -> address.getCustomerId().equals(customerId))
                .toList();
    }

    @Override
    public Long save(Address address) {
        log.debug("Mock repository: save address for customer_id={}", address.getCustomerId());
        Long newId = ID_GENERATOR.incrementAndGet();
        address.setId(newId);
        ADDRESSES.add(address);
        return newId;
    }
}
