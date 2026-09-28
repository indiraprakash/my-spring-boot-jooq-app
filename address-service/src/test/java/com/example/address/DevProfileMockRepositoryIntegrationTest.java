package com.example.address;

import com.example.address.repository.AddressRepository;
import com.example.address.repository.MockAddressRepository;
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
    private AddressRepository addressRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void devProfileUsesMockRepositoryWithoutConfiguringADatabase() {
        assertThat(addressRepository).isInstanceOf(MockAddressRepository.class);
        assertThat(applicationContext.getBeansOfType(DataSource.class)).isEmpty();
    }
}
