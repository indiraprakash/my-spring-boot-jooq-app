package com.example.mockdata.address;

public record MockAddressRecord(
        Long id,
        Long customerId,
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
