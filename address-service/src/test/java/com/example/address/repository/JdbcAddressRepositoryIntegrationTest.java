package com.example.address.repository;

import com.example.address.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
@Testcontainers
class JdbcAddressRepositoryIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:17-alpine")
            .withDatabaseName("customerdb")
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScript("address-test-schema.sql");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRES::getDriverClassName);
        registry.add("spring.flyway.enabled", () -> true);
    }

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void flywayRunsAgainstPostgresAndLoadsSeedData() {
        assertThat(addressRepository.findAll())
                .hasSize(3)
                .extracting(Address::getCity)
                .containsExactly("New York", "Los Angeles", "Chicago");
    }

    @Test
    void repositoryFindsAddressesByCustomerId() {
        assertThat(addressRepository.findByCustomerId(2L))
                .singleElement()
                .satisfies(address -> {
                    assertThat(address.getStreet()).isEqualTo("456 Oak Ave");
                    assertThat(address.getPostalCode()).isEqualTo("90001");
                });
    }

    @Test
    void repositoryPersistsAnAddressAndReturnsGeneratedId() {
        Address newAddress = new Address(null, 1L, "10 Test St", "Boston", "MA", "02108", "USA");

        Long generatedId = addressRepository.save(newAddress);

        assertThat(generatedId).isPositive();
        
        Address savedAddress = new Address(generatedId, 1L, "10 Test St", "Boston", "MA", "02108", "USA");
        assertThat(addressRepository.findById(generatedId)).contains(savedAddress);
    }
}
