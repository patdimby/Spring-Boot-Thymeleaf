🔐 Spring Boot Login and Registration Application





## A secure and user-friendly login & registration system

✨ Features
-------------
+ 📝 User registration with validation
+ 🔐 Secure login with Spring Security
+ 👤 Role-based access control (USER, ADMIN)
+ 🎨 Responsive UI using Thymeleaf + Bootstrap
+ 🗃️ MySQL-backed user data
+ 📊 Health & metrics with Spring Boot Actuator
+ ✨ Minimal boilerplate using Lombok
+ 🖼️ Demo Screenshots
(Replace with your actual screenshots or demo GIFs)

🧱 Tech Stack
-------------
### Tech	        Description

+ Spring Boot	    Java backend framework
+ Spring Security	Authentication & Authorization
+ Spring Data JPA	ORM and DB layer
+ Thymeleaf	        Template engine for dynamic HTML
+ Bootstrap and	CSS framework for responsive design
+ MySQL	            Relational database
+ Lombok	        Java boilerplate reducer
+ Spring Boot Actuator	Observability with health/metrics

📁 Project Structure

```
src/
=======
├── main/
   ├── java\com\patdimby\simplerest
   ├── config/           # Security configuration
   ├── controller/       # Web controllers (login, register, dashboard)
   ├── model/            # User entity and roles
   ├── repository/       # JPA repositories
   ├── service/          # UserService, security logic
   └── SimplerestApplication.java  # Spring Boot main class
│   └── resources/
│       ├── static/           # Bootstrap CSS, images, favicon
│       ├── templates/        # Thymeleaf templates
│       │   ├── login.html
│       │   ├── register.html
│       │   ├── dashboard.html
│       │   └── fragments/    # Reusable layouts (header, footer, etc.)
│       └── application.yml   # Configuration
└── test/                     # Unit and integration tests
```
🧩 Thymeleaf Template Structure

```
=======
templates/
├── fragments/
│   ├── header.html
│   └── footer.html
├── login.html
├── register.html
└── dashboard.html
Example login.html snippet:
```
<form th:action="@{/login}" method="post">
  <input type="text" name="username" class="form-control" placeholder="Username" required />
  <input type="password" name="password" class="form-control" placeholder="Password" required />
  <button type="submit" class="btn btn-primary">Login</button>
</form>

⚙️ Configuration (application.properties)

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



🙌 Acknowledgements
Spring Boot Docs

Spring Security Guide

Thymeleaf

Bootstrap

📫 Contact
Author: @yourusername
📧 you@example.com
