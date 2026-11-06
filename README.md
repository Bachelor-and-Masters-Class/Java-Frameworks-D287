# Inventory App

**WGU D287 · Java Frameworks**

A web app that tracks parts and products for a manufacturer. Parts are either made in-house (with a machine ID) or bought from a vendor (with a company name). Products are built from parts.

## Business rules it enforces

- A part's stock must stay between its minimum and maximum.
- A part can't be deleted while a product still uses it.
- You can't buy more of a product than its parts allow.
- Product prices must pass price checks.

These rules are checked with custom validators the app defines, not left to the user:
`ValidDeletePart`, `ValidEnufParts` and `ValidProductPrice`.

## How it's built

- **Parts:** one base `Part` type, with `InhousePart` and `OutsourcedPart` built on top of it (inheritance, saved with JPA).
- **Products:** linked to many parts, and parts to many products.
- **Layers:** repositories for the database, services for logic, controllers for web pages.
- **Pages:** server-rendered with Thymeleaf. Forms for adding and editing, plus confirmation pages.
- **Tests:** JUnit tests for parts, products, the repository and the service.

## Run it

```
./mvnw spring-boot:run
```

Then open the local port set in `application.properties`.

## Files

- `domain/`: parts and products
- `controllers/`: web pages and purchasing
- `service/`, `repositories/`: logic and database access
- `validators/`: custom business rules
- `templates/`: Thymeleaf pages
- `src/test/`: tests

**Built with:** Java, Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf, Maven, JUnit
