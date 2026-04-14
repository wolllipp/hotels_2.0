# Hotel Management REST API

RESTful API application for managing hotels built with Spring Boot.

## Technologies

- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- H2 Database (in-memory)
- Liquibase (database migrations)
- Swagger / OpenAPI 3.0
- Maven
- Lombok
- JUnit 5

## How to Run

```bash
mvn spring-boot:run
```

The application starts on port **8092**.

## API Endpoints

All endpoints use the `/property-view` prefix.

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/property-view/hotels` | Get all hotels (short info) |
| GET | `/property-view/hotels/{id}` | Get hotel details by ID |
| POST | `/property-view/hotels` | Create a new hotel |
| POST | `/property-view/hotels/{id}/amenities` | Add amenities to a hotel |
| GET | `/property-view/search` | Search hotels by parameters |
| GET | `/property-view/histogram/{param}` | Get hotel count histogram |

### Search Parameters

- `name` - Hotel name
- `brand` - Hotel brand
- `city` - City
- `country` - Country
- `amenities` - Amenity name

### Histogram Parameters

- `brand` - Group by brand
- `city` - Group by city
- `country` - Group by country
- `amenities` - Group by amenity

## Swagger UI

Available at: [http://localhost:8092/swagger-ui.html](http://localhost:8092/swagger-ui.html)

## H2 Console

Available at: [http://localhost:8092/h2-console](http://localhost:8092/h2-console)
- JDBC URL: `jdbc:h2:mem:hoteldb`
- Username: `sa`
- Password: (empty)

## Running Tests

```bash
mvn test
```

## Switching Database

To switch from H2 to another database (e.g., PostgreSQL), update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hoteldb
    driver-class-name: org.postgresql.Driver
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: none
```

And add the corresponding driver dependency to `pom.xml`.

## Project Structure

```
src/main/java/com/example/hotelapi/
├── config/          - Configuration classes (OpenAPI)
├── controller/      - REST controllers
├── dto/             - Data Transfer Objects
├── entity/          - JPA entities
├── repository/      - Spring Data repositories
├── service/         - Business logic layer
└── HotelApiApplication.java
```
