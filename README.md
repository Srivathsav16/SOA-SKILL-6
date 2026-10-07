# Skill Experiment 6 – Library Management Microservice 

## Architecture
- Eureka Server: http://localhost:8761
- Book Microservice: http://localhost:8004
- PostgreSQL database: library_db
- Eureka is included to demonstrate service discovery. No API Gateway is required.
- Spring Boot 3.5.15 + Spring Cloud 2025.0.3 + Java 17.

## Requirements
JDK 17+, Spring Tool Suite, PostgreSQL/pgAdmin 4.

## Database
Create a separate database named `library_db` in pgAdmin Query Tool:
```sql
CREATE DATABASE library_db;
```
Then check `BookService/src/main/resources/application.properties` and change username/password if needed.

## Import into STS
1. Extract the ZIP.
2. STS -> File -> Import -> Maven -> Existing Maven Projects.
3. Import `EurekaServer`.
4. Repeat for `BookService`.
5. Wait for Maven dependencies to download.

## Run order
1. Start PostgreSQL.
2. Run `EurekaServerApplication.java`.
3. Open http://localhost:8761 and verify the Eureka dashboard.
4. Run `BookServiceApplication.java`.
5. Open http://localhost:8004/api/books. Initially it returns `[]`.
6. Refresh Eureka and verify `BOOK-SERVICE` is registered.

## CRUD APIs
### Add Book
POST `http://localhost:8004/api/books`
```json
{"title":"Clean Code","author":"Robert C. Martin","isbn":"9780132350884"}
```
### View Books
GET `http://localhost:8004/api/books`
### View One Book
GET `http://localhost:8004/api/books/1`
### Update Book
PUT `http://localhost:8004/api/books/1`
```json
{"title":"Clean Code - Updated","author":"Robert C. Martin","isbn":"9780132350884"}
```
### Delete Book
DELETE `http://localhost:8004/api/books/1`

## Validation
- Title and author cannot be blank.
- ISBN must be 10 or 13 digits (optional hyphens/spaces for 13-digit ISBN).
- ISBN is unique.
- Missing ID -> 404.
- Invalid input -> 400.
- Duplicate ISBN -> 409.

## Suggested demonstration
POST two books -> GET all -> GET one -> PUT -> GET updated -> DELETE -> GET all -> test invalid input -> test duplicate ISBN.

## Project structure
```text
Skill_Experiment_6_Library_Microservice/
├── EurekaServer/
├── BookService/
└── database/create_database.sql
```

## Result
The Book Microservice performs Create, Read, Update and Delete operations using a separate PostgreSQL database, with Eureka service discovery and validation/exception handling.
