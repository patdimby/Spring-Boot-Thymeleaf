🔐 Spring Boot Login & Registration App





A secure and user-friendly login & registration system built with:

🧰 Spring Boot • Spring Security • Thymeleaf • Bootstrap • JPA • MySQL • Lombok • Actuator

✨ Features
📝 User registration with validation

🔐 Secure login with Spring Security

👤 Role-based access control (USER, ADMIN)

🎨 Responsive UI using Thymeleaf + Bootstrap

🗃️ MySQL-backed user data

📊 Health & metrics with Spring Boot Actuator

✨ Minimal boilerplate using Lombok

🖼️ Demo Screenshots
(Replace with your actual screenshots or demo GIFs)

Login Page	Registration Page

🧱 Tech Stack
Tech	Description
Spring Boot	Java backend framework
Spring Security	Authentication & Authorization
Spring Data JPA	ORM and DB layer
Thymeleaf	Template engine for dynamic HTML
Bootstrap 5	CSS framework for responsive design
MySQL	Relational database
Lombok	Java boilerplate reducer
Spring Boot Actuator	Observability with health/metrics

📁 Project Structure
bash
Copier
Modifier
src/
├── main/
│   ├── java/com/example/app/
│   │   ├── config/           # Security configuration
│   │   ├── controller/       # Web controllers (login, register, dashboard)
│   │   ├── model/            # User entity and roles
│   │   ├── repository/       # JPA repositories
│   │   ├── service/          # UserService, security logic
│   │   └── Application.java  # Spring Boot main class
│   └── resources/
│       ├── static/           # Bootstrap CSS, images, favicon
│       ├── templates/        # Thymeleaf templates
│       │   ├── login.html
│       │   ├── register.html
│       │   ├── dashboard.html
│       │   └── fragments/    # Reusable layouts (header, footer, etc.)
│       └── application.yml   # Configuration
└── test/                     # Unit and integration tests
🧩 Thymeleaf Template Structure
css
Copier
Modifier
templates/
├── fragments/
│   ├── header.html
│   └── footer.html
├── login.html
├── register.html
└── dashboard.html
Example login.html snippet:

html
Copier
Modifier
<form th:action="@{/login}" method="post">
  <input type="text" name="username" class="form-control" placeholder="Username" required />
  <input type="password" name="password" class="form-control" placeholder="Password" required />
  <button type="submit" class="btn btn-primary">Login</button>
</form>
⚙️ Configuration (application.yml)
yaml
Copier
Modifier
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/user_db
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
🧪 User Roles
You can seed users with roles like:

ROLE_USER

ROLE_ADMIN

In data.sql (optional):

sql
Copier
Modifier
INSERT INTO users (id, username, password, role) VALUES
(1, 'user', '$2a$10$encodedpassword', 'ROLE_USER');
🌐 Actuator Endpoints
Endpoint	Description
/actuator/health	App health status
/actuator/metrics	Performance metrics
/actuator/env	App environment info

🐳 Optional: Run with Docker
Create a Dockerfile:

Dockerfile
Copier
Modifier
FROM openjdk:17-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
Build and Run:

bash
Copier
Modifier
docker build -t springboot-login-app .
docker run -p 8080:8080 springboot-login-app
🧪 Testing
Run all tests:

bash
Copier
Modifier
./mvnw test
📜 License
This project is licensed under the MIT License.

🙌 Acknowledgements
Spring Boot Docs

Spring Security Guide

Thymeleaf

Bootstrap

📫 Contact
Author: @yourusername
📧 you@example.com