package com.example.address.controller;

import com.example.address.model.Address;
import com.example.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        log.info("GET /api/addresses - retrieving all addresses");
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        log.info("GET /api/addresses/{} - retrieving address by id", id);
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Address> getAddressesByCustomerId(@PathVariable Long customerId) {
        log.info("GET /api/addresses/customer/{} - retrieving addresses by customer id", customerId);
        return addressService.getAddressesByCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        log.info("POST /api/addresses - creating new address for customer: {}", address.getCustomerId());
        Long addressId = addressService.createAddress(address);
        address.setId(addressId);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
}
