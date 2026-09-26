package com.example.address.service;

import com.example.address.model.Address;
import com.example.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        log.debug("Fetching all addresses");
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        log.debug("Fetching address by id: {}", id);
        return addressRepository.findById(id);
    }

    public List<Address> getAddressesByCustomerId(Long customerId) {
        log.debug("Searching addresses by customerId: {}", customerId);
        return addressRepository.findByCustomerId(customerId);
    }

    public Long createAddress(Address address) {
        log.info("Creating new address for customer: {}", address.getCustomerId());
        return addressRepository.save(address);
    }
}
