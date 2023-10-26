## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Code Coverage Analysis](#code-coverage-analysis)

## General info

This is a RESTful application that provides a REST API for retrieving price information. It allows you to query prices
based on specific criteria, making it a valuable tool for applications that require price data. One of the notable
design choices in this project is the decision to implement querying and filtering directly in the database rather than
handling these operations within the Java application itself. This decision was made with a focus on efficiency and
optimization.

## Technologies

Project is created with:

* Java 17: The version of Java provides which becoming industry standard and improved performance, language features,
  and long-term support for the project.

* Maven 3.9.5: Maven is used as the build tool for its robust dependency management and build lifecycle capabilities.

* Spring Boot 3.1.4: Spring Boot simplifies the development of production-ready applications by providing pre-configured
  templates and a range of built-in features. It's chosen for rapid development and deployment.

* H2 In-Memory Database 2.1.212: H2 is used as an in-memory database for its lightweight and fast data storage, making
  it suitable for development and testing purposes.

* Flyway 9.16.3: Flyway is used for database schema migration. It simplifies database version control and management,
  ensuring that the database schema evolves with the application. Here flyway is used for preload data for testing
  purpose.

* Lombok 1.18.30: Lombok reduces boilerplate code in Java classes, making the codebase cleaner and more maintainable.

* OpenAPI/Swagger (springdoc-openapi-starter-webmvc-ui) 2.2.0: OpenAPI/Swagger is used for documenting the REST API. It
  provides a convenient way to describe the API, allowing for easier integration and client-side code generation.

* JaCoCo 0.8.11: JaCoCo is used for code coverage analysis. It helps ensure the quality of the codebase by measuring the
  extent to which your code is covered by tests.

## Design Patterns

**Design patterns used in the project**

1. **Model-View-Controller (MVC) Pattern**:

- The `PricingController` class represents the Controller layer, handling HTTP requests and delegating business logic to
  the service layer.
- The `PriceService` and `PriceServiceImp` classes represent the Service layer, encapsulating business logic and
  interacting with the repository.
- The `PriceEntry` and `Brand` classes are used as Model objects.
- The `PriceResponse` classes are used as view object which is convert to JSON and presented on client site.

2. **Repository Pattern**:

- The `PriceRepository` interface defines methods for database operations. It leverages Spring Data JPA, which
  simplifies data access and provides common database operations.

3. **Dependency Injection (DI) Pattern**:

- Constructor injection is used in the `PricingController` and `PriceServiceImp` classes to inject dependencies,
  following the best practice of loose coupling and facilitating testing.

4. **DTO (Data Transfer Object)**:

- The `PriceResponse` class is used as a Data Transfer Object to transfer data between the Controller and the client.
  It's a clean way to decouple the internal entity structure from the external representation.

5. **Exception Handling Pattern**:

- The application uses custom exception classes (`PriceEntryNotFoundException`, `ErrorResponse`,
  and `PriceEntryNotFoundAdvice`) to handle exceptions and provide meaningful error responses. This is a best practice
  for error handling in a Spring Boot application.

6. **Annotation-Based Configuration**:

- Annotations such as `@RestController`, `@Service`, and `@Autowired` are used to configure and define Spring
  components, which reduces the need for XML configuration.

7. **Swagger Annotations**:

- Annotations from Swagger (e.g., `@Operation`, `@Parameter`, `@Tag`) are used for documenting and describing the API
  endpoints.

8. **Builder Pattern**:

- The `@Builder` annotation is used in the `PriceResponse`, `PriceEntry` and `Brand` classes to generate a builder
  method for creating instances. This can make the code cleaner and more readable when creating complex objects.

These patterns help this Spring Boot application to make it more maintainable,
testable, and easier to understand. The MVC pattern, in particular, separates concerns, making the codebase modular and
promoting code usability.

## Setup

To run this project, you will need to have JDK 17 or above installed. You can run it from an integrated development
environment (IDE) like IntelliJ IDEA or run it using Maven, although additional setup is required. More information on
running a Spring Boot application with Maven can be found here.

The project loads some test data automatically into the in-memory H2 database during startup using Flyway scripts
located in the resources/db/migration directory. You can inspect this data using the H2 Console
at http://localhost:8080/h2-console/. Use the following JDBC URL, username, and password for access:

    JDBC URL: jdbc:h2:mem:testdb
    User: su
    Password: su

To test the API, you can make requests to endpoints like:

    GET http://localhost:8080/api/price?applicationDate=2020-06-15T00:00:00&productId=35455&brandId=1

You can use a web browser or tools like Postman to make requests. Additional information about available endpoints and
their details can be found once the application is running at http://localhost:8080/swagger-ui/index.html.

## Code Coverage Analysis

Code coverage analysis is performed using JaCoCo version 0.8.11. Currently, the code coverage stands at **82%**. To run
code coverage analysis, execute the following Maven command:

```
mvn clean install
```

The code coverage report will be generated and can be found in the target/site/jacoco/index.html. This report helps you
understand how well your code is covered by tests and assists in improving the quality and reliability of your
application.