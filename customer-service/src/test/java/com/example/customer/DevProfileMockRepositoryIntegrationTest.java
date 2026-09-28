package com.example.customer;

import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.MockCustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class DevProfileMockRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void devProfileUsesMockRepositoryWithoutConfiguringADatabase() {
        assertThat(customerRepository).isInstanceOf(MockCustomerRepository.class);
        assertThat(applicationContext.getBeansOfType(DataSource.class)).isEmpty();
    }
}
