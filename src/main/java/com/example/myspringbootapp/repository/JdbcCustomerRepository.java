package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.model.Customer;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.generated.jooq.tables.Customer.CUSTOMER;

@Repository
@Profile("prod")
public class JdbcCustomerRepository implements CustomerRepository {

    private final DSLContext dsl;

    public JdbcCustomerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return dsl.select(
                        CUSTOMER.ID,
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.DATE_OF_BIRTH
                )
                .from(CUSTOMER)
                .where(CUSTOMER.ID.eq(id))
                .fetchOptional(record -> new Customer(
                        record.get(CUSTOMER.ID),
                        record.get(CUSTOMER.FIRST_NAME),
                        record.get(CUSTOMER.LAST_NAME),
                        record.get(CUSTOMER.DATE_OF_BIRTH)
                ));
    }

    @Override
    public List<Customer> findAll() {
        return dsl.select(
                        CUSTOMER.ID,
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.DATE_OF_BIRTH
                )
                .from(CUSTOMER)
                .orderBy(CUSTOMER.ID.asc())
                .fetch(record -> new Customer(
                        record.get(CUSTOMER.ID),
                        record.get(CUSTOMER.FIRST_NAME),
                        record.get(CUSTOMER.LAST_NAME),
                        record.get(CUSTOMER.DATE_OF_BIRTH)
                ));
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return dsl.select(
                        CUSTOMER.ID,
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.DATE_OF_BIRTH
                )
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq(firstName))
                .orderBy(CUSTOMER.ID.asc())
                .fetch(record -> new Customer(
                        record.get(CUSTOMER.ID),
                        record.get(CUSTOMER.FIRST_NAME),
                        record.get(CUSTOMER.LAST_NAME),
                        record.get(CUSTOMER.DATE_OF_BIRTH)
                ));
    }

    @Override
    public List<Customer> findByDateOfBirth(LocalDate dateOfBirth) {
        return dsl.select(
                        CUSTOMER.ID,
                        CUSTOMER.FIRST_NAME,
                        CUSTOMER.LAST_NAME,
                        CUSTOMER.DATE_OF_BIRTH
                )
                .from(CUSTOMER)
                .where(CUSTOMER.DATE_OF_BIRTH.eq(dateOfBirth))
                .orderBy(CUSTOMER.ID.asc())
                .fetch(record -> new Customer(
                        record.get(CUSTOMER.ID),
                        record.get(CUSTOMER.FIRST_NAME),
                        record.get(CUSTOMER.LAST_NAME),
                        record.get(CUSTOMER.DATE_OF_BIRTH)
                ));
    }
}
