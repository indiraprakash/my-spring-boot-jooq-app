package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.model.Customer;
import org.jooq.DSLContext;
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
import java.util.List;

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

    @Autowired
    private DSLContext dsl;

    @Test
    void flywayCreatesSchemaAndRepositoryReadsSeedData() {
        List<Customer> customers = customerRepository.findAll();

        assertThat(customers).hasSize(3);
        assertThat(customers.getFirst().firstName()).isEqualTo("John");
        assertThat(customers.getFirst().dateOfBirth()).isEqualTo(LocalDate.of(1990, 5, 21));
    }

    @Test
    void repositoryFindsCustomerByFirstName() {
        assertThat(customerRepository.findByFirstName("Jane"))
                .extracting(Customer::lastName)
                .containsExactly("Smith");
    }

    @Test
    void repositoryUsesTheConfiguredJdbcDataSource() {
        Integer customerCount = dsl.fetchCount(org.jooq.impl.DSL.table("customer"));

        assertThat(customerCount).isEqualTo(3);
    }
}
