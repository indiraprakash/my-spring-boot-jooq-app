package com.example.customer.config;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class CustomerConfig {

    private static final Logger log = LoggerFactory.getLogger(CustomerConfig.class);

    @Bean
    public DSLContext dslContext(DataSource dataSource) {
        log.info("Initializing jOOQ DSLContext for Customer service with PostgreSQL dialect");
        return DSL.using(dataSource, org.jooq.SQLDialect.POSTGRES);
    }
}
