package com.example.hotelapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hotelApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Management API")
                        .description("RESTful API for managing hotels, amenities, and search operations")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Developer")
                                .email("developer@example.com")));
    }
}
