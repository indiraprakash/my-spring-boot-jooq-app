# Cleanup Plan for Spring Modulith Refactoring

This document outlines the files and directories that should be deleted as part of the refactoring to Spring Modulith monolith architecture.

## Directories to Delete

### 1. Root-level monolith `src/` directory
```
src/
├── main/
│   ├── java/com/example/myspringbootapp/
│   │   ├── MySpringBootAppApplication.java
│   │   ├── config/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       ├── application-dev.yml
│       ├── application-prod.yml
│       ├── application.yml
│       └── db/migration/postgresql/
└── test/
    └── java/com/example/myspringbootapp/
```
**Reason:** This is the old root-level monolith application. All code has been migrated to `spring-boot-web-shop/src/main/java/com/example/springshop/`.

### 2. `customer-service/` module
```
customer-service/
├── pom.xml
└── src/
```
**Reason:** Customer domain is now part of the unified Spring Modulith application at `spring-boot-web-shop/src/main/java/com/example/springshop/customer/`.

### 3. `address-service/` module
```
address-service/
├── pom.xml
└── src/
```
**Reason:** Address domain is now part of the unified Spring Modulith application at `spring-boot-web-shop/src/main/java/com/example/springshop/address/`.

### 4. `common-lib/` module (if not needed)
```
common-lib/
├── pom.xml
└── src/
```
**Reason:** Shared exception handling is now integrated into the Spring Modulith application. If there are unique utilities, they can be moved into `spring-boot-web-shop/src/main/java/com/example/springshop/shared/`.

## Files to Delete

- None at the root level, only directories as listed above.

## Summary

**Total directories to remove:** 4 (src/, customer-service/, address-service/, common-lib/)
**Lines of code consolidated:** Thousands
**Result:** Single deployable Spring Modulith application with clear module boundaries

## After Cleanup

Repository structure will be:

```
my-spring-boot-jooq-app/
├── pom.xml (updated parent POM)
├── README.md
├── LICENSE
├── .gitignore
└── spring-boot-web-shop/
    ├── pom.xml
    ├── README.md
    └── src/
        ├── main/
        │   ├── java/com/example/springshop/
        │   └── resources/
        └── test/
            └── java/com/example/springshop/
```

## Implementation

To execute the cleanup:

```bash
# Delete old modules
rm -rf src/
rm -rf customer-service/
rm -rf address-service/
rm -rf common-lib/

# Commit changes
git add -A
git commit -m "Remove obsolete modules and old monolith - migrated to Spring Modulith"
```

**Note:** This refactoring eliminates:
- Duplicate Spring Boot applications (CustomerServiceApplication, AddressServiceApplication, MySpringBootAppApplication)
- Multiple entry points and confusion about which application to run
- Inter-module dependencies on common-lib
- Complex multi-module build setup

**Benefits:**
- Single, clear entry point: `SpringShopApplication`
- True modular monolith with Spring Modulith framework
- Enforced module boundaries
- Easier testing and deployment
- Clear responsibility per domain module
