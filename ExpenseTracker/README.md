# 💸 Expense Tracker API

A Spring Boot-based backend application for tracking expenses with categorized transactions, summaries, and advanced filtering features.


---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
-Spring Data JPA
-Database: H2 (in-memory) / MySQL
(Choose based on environment: local development (H2) or production/cloud (MySQL); cloud DBs may require SSL config)
-Swagger/OpenAPI (for interactive API docs)
-JUnit & Mockito (for unit and integration testing)
---

## 🚀 How to Run

```bash
mvn clean install
mvn spring-boot:run


Project Structure
src/
 └── main/
     ├── java/
     │   └── com.example.demo/
     │       ├── controller/
     │       ├── service/
     │       ├── repository/
     │       ├── model/
     │       └── ExpenseTrackerApplication.java
     └── resources/
         ├── application.properties
         └── data.sql
		     queries.sql
			 schema.sql
			 
 The .sql files are included to help validate data after each API call or to use with Testcontainers while testing use cases.

Run Tests

mvn test

Or run build and test together:

mvn clean install -DskipTests=true

Or skip tests:

mvn clean install -DskipTests=true


Swagger API Docs

Once the app is running, visit:
📍 http://localhost:8080/swagger-ui/index.html




📦 Future Improvements
Authentication (JWT/OAuth2)

Pagination and sorting

Dockerize app

UI integration



🧑‍💻 Author
Maintained by Steffi




.gitignore Suggestions
Add the following to .gitignore:

/target/
*.log
.classpath
.settings/
.idea/
*.iml


Security Note
Avoid committing sensitive information like DB credentials or tokens in application.properties or application.yml.


Initialize Git Repository

git init
git add .
git commit -m "Initial commit with Swagger + README"
git remote add origin https://github.com/Steffi-Cloud/Repo1.git
git push -u origin main

