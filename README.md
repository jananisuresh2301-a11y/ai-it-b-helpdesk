# Medical Shop Management System

Starter code based on the project report `JANU REPPO.docx`.

## Stack
- Java 17, Spring Boot, Maven
- Spring Data JPA, MySQL
- HTML, CSS, JavaScript

## Run
1. Install JDK 17 and Maven.
2. Create a MySQL database named `medical_shop`.
3. Edit `src/main/resources/application.properties` with your MySQL username/password.
4. Run: `mvn spring-boot:run`
5. Open http://localhost:8080

## API endpoints
- `GET /api/medicines` — list medicines
- `POST /api/medicines` — add a medicine
- `PUT /api/medicines/{id}/stock?quantity=2` — reduce stock by quantity
- `POST /api/auth/login` — demo login validation (not production authentication)

## Important
The report contains code fragments rather than a complete source repository. This ZIP organizes a runnable starter project from the report's described modules. Prescription verification and payment are demo workflows only; do not use for real prescriptions, customer data, or live payments without proper authentication, validation, and security review.
