# Spring Boot Web Shop - Spring Modulith Application

A modern Spring Modulith-based web shop application demonstrating true modular monolith architecture with clear module boundaries.

## Architecture

This application follows the **Spring Modulith** pattern for building modular monoliths:

### Modules

#### Customer Module (`com.example.springshop.customer`)
- Manages customer data and queries
- **Components:**
  - `Customer` model (entity)
  - `CustomerRepository` interface
  - `JdbcCustomerRepository` (JDBC/jOOQ implementation for prod)
  - `MockCustomerRepository` (in-memory for dev)
  - `CustomerService` (business logic)
  - `CustomerController` (REST API)

**Endpoints:**
- `GET /api/customers` - List all customers
- `GET /api/customers/{id}` - Get customer by ID
- `GET /api/customers/search/by-firstname?firstName=John` - Search by first name
- `GET /api/customers/search/by-dob?dateOfBirth=1990-05-21` - Search by date of birth

#### Address Module (`com.example.springshop.address`)
- Manages addresses associated with customers
- **Components:**
  - `Address` model (entity)
  - `AddressRepository` interface
  - `JdbcAddressRepository` (JDBC/jOOQ implementation for prod)
  - `MockAddressRepository` (in-memory for dev)
  - `AddressService` (business logic)
  - `AddressController` (REST API)

**Endpoints:**
- `GET /api/addresses` - List all addresses
- `GET /api/addresses/{id}` - Get address by ID
- `GET /api/addresses/customer/{customerId}` - Get addresses by customer
- `POST /api/addresses` - Create new address

### Shared Components

#### Configuration (`com.example.springshop.config`)
- `JooqConfig` - jOOQ DSLContext bean configuration

#### Database
- PostgreSQL (production)
- Flyway migrations
- Shared schema for all domains

## Building & Running

### Prerequisites
- Java 25+
- Maven 3.8+
- PostgreSQL 15+

### Build
```bash
mvn clean package
```

### Run

**Production mode (PostgreSQL):**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

**Development mode (in-memory mock data):**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Database Setup (Production)

Create PostgreSQL database:
```sql
CREATE DATABASE customerdb;
```

Flyway will automatically run migrations on startup.

## Module Boundaries

The `ModularityTest` verifies that:
- Modules only communicate through their public APIs
- No inappropriate cross-module dependencies exist
- Each module maintains proper encapsulation

Run modularity tests:
```bash
mvn test -Dtest=ModularityTest
```

## Integration Tests

Integration tests use TestContainers to run PostgreSQL in Docker:
```bash
mvn verify
```

## Technology Stack

- **Spring Boot** 4.1.1 - Framework
- **Spring Modulith** 2.0.0 - Modular architecture
- **jOOQ** 3.20.5 - Type-safe SQL builder
- **Flyway** 10.13.0 - Database migrations
- **PostgreSQL** - Production database
- **Testcontainers** 1.20.6 - Docker-based integration tests
- **Lombok** 1.18.48 - Boilerplate reduction

## Project Structure

```
spring-boot-web-shop/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/springshop/
    │   │   ├── SpringShopApplication.java
    │   │   ├── config/
    │   │   ├── customer/
    │   │   │   ├── model/
    │   │   │   ├── repository/
    │   │   │   ├── service/
    │   │   │   └── controller/
    │   │   └── address/
    │   │       ├── model/
    │   │       ├── repository/
    │   │       ├── service/
    │   │       └── controller/
    │   └── resources/
    │       ├── application.yml
    │       ├── application-prod.yml
    │       └── db/migration/postgresql/
    └── test/
        └── java/com/example/springshop/
```

## References

- [Spring Modulith Documentation](https://spring.io/projects/spring-modulith)
- [jOOQ Documentation](https://www.jooq.org/)
- [Flyway Documentation](https://flywaydb.org/)
- [Testcontainers Documentation](https://www.testcontainers.org/)
