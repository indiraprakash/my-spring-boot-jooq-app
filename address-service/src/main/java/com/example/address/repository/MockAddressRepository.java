package com.example.address.repository;

import com.example.address.model.Address;
import com.example.mockdata.address.MockAddressData;
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

    private static final List<Address> ADDRESSES = new ArrayList<>(MockAddressData.addresses().stream()
            .map(address -> new Address(
                    address.id(),
                    address.customerId(),
                    address.street(),
                    address.city(),
                    address.state(),
                    address.postalCode(),
                    address.country()
            ))
            .toList());

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
