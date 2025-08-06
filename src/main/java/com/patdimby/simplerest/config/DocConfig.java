package com.patdimby.simplerest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "RANOELISON Dimbisoa Patrick",
                        email = "patdimby@outlook.fr",
                        url = "https://github/patdimby"
                ),
                description = "Document API ",
                version = "1.0.0",
                title = "OpenAPI - Dimbisoa"

        )
)
@Configuration
public class DocConfig {


}