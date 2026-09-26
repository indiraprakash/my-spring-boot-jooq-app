package com.example.address.repository;

import com.example.address.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findById(Long id);
    List<Address> findAll();
    List<Address> findByCustomerId(Long customerId);
    Long save(Address address);
}
