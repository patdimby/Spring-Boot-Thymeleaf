# Spring-Boot-Thymeleaf
A secure and user-friendly login & registration system.

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
```

⚙️ Configuration (application.properties)
```properties
spring.application.name=simplerest

server.port = 8082

# DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://localhost:3306/employees?create
spring.datasource.username=user
spring.datasource.password=password
spring.data.jpa.repositories.enabled=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.generate-ddl=true
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update

# In order to display spring-boot-actuator endpoints, simply add the following property:
springdoc.show-actuator=true
springdoc.use-management-port=true

management.server.port=9090
# This property enables the swagger-ui endpoints to be exposed beneath the actuator base path.
management.endpoints.web.exposure.include=swagger-ui
# http://localhost:9090/actuator/swagger-ui

spring.thymeleaf.cache=false

# Show or not log for each sql query
spring.jpa.show-sql = true
logging.level.org.hibernate=ERROR
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE

logging.level.org.springframework.web=INFO
logging.level.guru.springframework.controllers=DEBUG
logging.file.name=logs/spring-boot-logging.log
```
🧪 User Roles

You can seed users with roles like:
+ ROLE_USER
+ ROLE_ADMIN

🙌 Acknowledgements
Spring Boot Docs

+ Spring Security Guide(https://spring.io/guides/gs/securing-web)
+ Thymeleaf(https://www.thymeleaf.org/)
+ Bootstrap(https://getbootstrap.com/)

📫 Contact
Author: Patrick Dimbisoa
📧 patdimby@outlook.fr
