package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
@Testcontainers
class JdbcCustomerRepositoryIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:17-alpine")
            .withDatabaseName("customerdb")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRES::getDriverClassName);
        registry.add("spring.flyway.enabled", () -> true);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void flywayRunsAgainstPostgresAndLoadsSeedData() {
        assertThat(customerRepository.findAll())
                .hasSize(3)
                .extracting(Customer::getFirstName)
                .containsExactly("John", "Jane", "Alice");
    }

    @Test
    void repositoryQueriesTheContainerizedDatabase() {
        assertThat(customerRepository.findByFirstName("Jane"))
                .singleElement()
                .satisfies(customer -> {
                    assertThat(customer.getLastName()).isEqualTo("Smith");
                    assertThat(customer.getDateOfBirth()).isEqualTo(LocalDate.of(1987, 11, 14));
                });
    }
}
