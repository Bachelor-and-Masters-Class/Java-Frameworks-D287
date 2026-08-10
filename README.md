**Overview**

This repository contains my completed submission for the WGU course **D287: Java Frameworks**.

The project is a **full-stack inventory management application** built with Spring Boot and Thymeleaf. It manages parts and products for a manufacturing business, supporting two distinct part sourcing types, associations between products and the parts they require, and a set of custom business rules enforced through validation.

The course focus is the practical application of the Spring framework ecosystem: inversion of control, JPA persistence, MVC request handling, server-side templating, custom validation annotations, and unit testing.

**Scenario**

A manufacturer needs an internal system to track inventory. Parts come from two sources: those manufactured in-house, identified by a machine ID, and those purchased from external vendors, identified by a company name. Products are assembled from one or more parts.

The business imposes rules that the application must enforce rather than trusting user input:

-Inventory for any part must remain within its defined minimum and maximum bounds

-A part cannot be deleted while it is still associated with a product

-A product cannot be purchased in a quantity greater than the available inventory of its constituent parts

-Product pricing must satisfy defined validation constraints

**Project Objectives**

-Model an inventory domain using inheritance for part sourcing types

-Implement JPA persistence and repository access for all entities

-Build a Spring MVC controller layer covering the full create, read, update, and delete lifecycle

-Deliver server-rendered views with Thymeleaf templates

-Implement custom validation annotations for business rules that standard validation cannot express

-Write unit tests covering domain logic, repositories, and services

**Architecture**

**Domain Layer**
A Part base type is extended by InhousePart and OutsourcedPart, using JPA inheritance so that both types persist through a shared abstraction while retaining their type-specific fields. Product models the assembled item and its many-to-many association with parts.

**Repository Layer**
Spring Data repositories provide persistence access for parts, products, and each part subtype.

**Service Layer**
Service interfaces and implementations encapsulate inventory logic, keeping controllers focused on request handling and view resolution.

**Controller Layer**
Controllers handle adding in-house parts, adding outsourced parts, managing products, associating parts with products, and processing purchases. Each operation resolves to a Thymeleaf view.

**Custom Validators**
The application implements purpose-built validation annotations with matching validator classes:

-**ValidDeletePart / DeletePartValidator**: blocks deletion of a part that is still associated with a product

-**ValidEnufParts / EnufPartsValidator**: prevents a purchase that would exceed available part inventory

-**ValidProductPrice / PriceProductValidator**: enforces product pricing constraints

**View Layer**
Thymeleaf templates render forms for adding and updating parts and products, confirmation pages for each operation, and an about page, with a static index page as the entry point.

**Testing**
JUnit tests cover the domain model including part subtypes and products, the in-house part repository, and the in-house part service.

**Technologies**

-Java and Spring Boot

-Spring MVC

-Spring Data JPA and Hibernate

-Thymeleaf server-side templating

-Bean Validation with custom constraint annotations

-JUnit

-Maven

**Skills Demonstrated**

-Spring framework application development

-Object-oriented inheritance modeling with JPA

-MVC architecture and request lifecycle handling

-Server-side templating and form binding

-Custom validation annotation and validator implementation

-Business rule enforcement at the application layer

-Repository and service layer design

-Unit testing across domain, repository, and service layers

**How to Run**

```
./mvnw spring-boot:run
```

The application serves the inventory interface from the local Spring Boot port defined in the application properties.

**Repository Contents**

-`src/main/java/domain/`: Part, InhousePart, OutsourcedPart, and Product entities

-`src/main/java/controllers/`: MVC controllers for parts, products, and purchasing

-`src/main/java/service/`: Inventory service interfaces and implementations

-`src/main/java/repositories/`: Spring Data repositories

-`src/main/java/validators/`: Custom validation annotations and validator implementations

-`src/main/java/bootstrap/`: Startup seed data

-`src/main/resources/templates/`: Thymeleaf views and confirmation pages

-`src/test/`: JUnit test suite
