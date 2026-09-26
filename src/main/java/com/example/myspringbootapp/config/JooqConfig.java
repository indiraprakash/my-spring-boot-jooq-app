package com.example.myspringbootapp.config;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class JooqConfig {

    @Bean
    public DSLContext dslContext(DataSource dataSource) {
        log.info("Initializing jOOQ DSLContext with PostgreSQL dialect");
        return DSL.using(dataSource, org.jooq.SQLDialect.POSTGRES);
    }
}
