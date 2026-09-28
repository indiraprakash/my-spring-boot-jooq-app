# my-spring-boot-jooq-app

Multi-module Spring Boot application using jOOQ.

## Modules

- `common-lib` - shared exception handling and common components
- `mock-data-module` - standalone mock data project used by development profiles
- `customer-service` - customer APIs, service layer, repository, and migrations
- `address-service` - address APIs, service layer, repository, and migrations

## Development profile

The `dev` profile now runs without any database connection. `customer-service` and `address-service`
load their seed responses from `mock-data-module`, while the `prod` profile keeps the PostgreSQL +
jOOQ/Flyway setup.
