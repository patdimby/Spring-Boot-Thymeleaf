package com.patdimby.simplerest;

import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.repository.UserRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using the Bearer scheme"
)
@SpringBootApplication
public class SimplerestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplerestApplication.class, args);
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Add security requirement globally
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        // Fully qualified name for SecurityScheme to avoid name conflict
                        .addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name("bearerAuth")
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("patdimby@outlook.fr").isEmpty()) {
                User admin = new User();
                admin.setUsername("patdimby");
                admin.setEmail("patdimby@outlook.fr");
                admin.setPassword(passwordEncoder.encode("Masterkey1"));
                admin.setRole(UserRole.ROLE_ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Created super user : patdimby@outlook.fr / Masterkey1");
            } else {
                System.out.println("✅ Super user already exist in database.");
            }
        };
    }
}

