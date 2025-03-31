package com.patdimby.simplerest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplerestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplerestApplication.class, args);
	}

	public OpenAPI getOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("HoN Core Auth API")
						.description("Authentication & authorization API")
						.version("v1"));
	}

}

