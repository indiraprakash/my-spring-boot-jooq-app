package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

@Repository
@Profile("prod")
public class JdbcCustomerRepository implements CustomerRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcCustomerRepository.class);

    private static final Table<?> CUSTOMER = table(name("customer"));
    private static final Field<Long> ID = field(name("id"), Long.class);
    private static final Field<String> FIRST_NAME = field(name("first_name"), String.class);
    private static final Field<String> LAST_NAME = field(name("last_name"), String.class);
    private static final Field<LocalDate> DATE_OF_BIRTH = field(name("date_of_birth"), LocalDate.class);

    private final DSLContext dsl;

    public JdbcCustomerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        log.debug("Executing jOOQ query: findById({})", id);
        return baseQuery()
                .where(ID.eq(id))
                .fetchOptional(this::toCustomer);
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Executing jOOQ query: findAll()");
        return baseQuery()
                .orderBy(ID.asc())
                .fetch(this::toCustomer);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        log.debug("Executing jOOQ query: findByFirstName({})", firstName);
        return baseQuery()
                .where(FIRST_NAME.eq(firstName))
                .orderBy(ID.asc())
                .fetch(this::toCustomer);
    }

    @Override
    public List<Customer> findByDateOfBirth(LocalDate dateOfBirth) {
        log.debug("Executing jOOQ query: findByDateOfBirth({})", dateOfBirth);
        return baseQuery()
                .where(DATE_OF_BIRTH.eq(dateOfBirth))
                .orderBy(ID.asc())
                .fetch(this::toCustomer);
    }

    private org.jooq.SelectJoinStep<?> baseQuery() {
        return dsl.select(ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH)
                .from(CUSTOMER);
    }

    private Customer toCustomer(org.jooq.Record record) {
        return new Customer(
                record.get(ID),
                record.get(FIRST_NAME),
                record.get(LAST_NAME),
                record.get(DATE_OF_BIRTH)
        );
    }
}
