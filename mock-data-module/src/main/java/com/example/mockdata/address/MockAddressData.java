package com.example.mockdata.address;

import java.util.List;

public final class MockAddressData {

    private static final List<MockAddressRecord> ADDRESSES = List.of(
            new MockAddressRecord(1L, 1L, "123 Main St", "New York", "NY", "10001", "USA"),
            new MockAddressRecord(2L, 2L, "456 Oak Ave", "Los Angeles", "CA", "90001", "USA"),
            new MockAddressRecord(3L, 3L, "789 Pine Rd", "Chicago", "IL", "60601", "USA")
    );

    private MockAddressData() {
    }

    public static List<MockAddressRecord> addresses() {
        return ADDRESSES;
    }
}
