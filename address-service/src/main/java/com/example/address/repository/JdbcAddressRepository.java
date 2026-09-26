package com.example.address.repository;

import com.example.address.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

@Repository
@Profile("prod")
@RequiredArgsConstructor
@Slf4j
public class JdbcAddressRepository implements AddressRepository {

    private static final Table<?> ADDRESS = table(name("address"));
    private static final Field<Long> ID = field(name("id"), Long.class);
    private static final Field<Long> CUSTOMER_ID = field(name("customer_id"), Long.class);
    private static final Field<String> STREET = field(name("street"), String.class);
    private static final Field<String> CITY = field(name("city"), String.class);
    private static final Field<String> STATE = field(name("state"), String.class);
    private static final Field<String> POSTAL_CODE = field(name("postal_code"), String.class);
    private static final Field<String> COUNTRY = field(name("country"), String.class);

    private final DSLContext dsl;

    @Override
    public Optional<Address> findById(Long id) {
        log.debug("Executing jOOQ query: findById({})", id);
        return baseQuery()
                .where(ID.eq(id))
                .fetchOptional(this::toAddress);
    }

    @Override
    public List<Address> findAll() {
        log.debug("Executing jOOQ query: findAll()");
        return baseQuery()
                .orderBy(ID.asc())
                .fetch(this::toAddress);
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        log.debug("Executing jOOQ query: findByCustomerId({})", customerId);
        return baseQuery()
                .where(CUSTOMER_ID.eq(customerId))
                .orderBy(ID.asc())
                .fetch(this::toAddress);
    }

    @Override
    public Long save(Address address) {
        log.debug("Executing jOOQ query: save address for customer_id={}", address.getCustomerId());
        return dsl.insertInto(ADDRESS)
                .columns(CUSTOMER_ID, STREET, CITY, STATE, POSTAL_CODE, COUNTRY)
                .values(
                        address.getCustomerId(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getPostalCode(),
                        address.getCountry()
                )
                .returning(ID)
                .fetchOne()
                .get(ID);
    }

    private org.jooq.SelectJoinStep<?> baseQuery() {
        return dsl.select(ID, CUSTOMER_ID, STREET, CITY, STATE, POSTAL_CODE, COUNTRY)
                .from(ADDRESS);
    }

    private Address toAddress(org.jooq.Record record) {
        return new Address(
                record.get(ID),
                record.get(CUSTOMER_ID),
                record.get(STREET),
                record.get(CITY),
                record.get(STATE),
                record.get(POSTAL_CODE),
                record.get(COUNTRY)
        );
    }
}
