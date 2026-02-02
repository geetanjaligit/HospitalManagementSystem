package com.project.hospitalManagement.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management API")
                        .description("Hospital Management Application"))
                .servers(List.of(
                    new Server().url("http://localhost:8081/api/v1"),
                    new Server().url("https://hospital-management.com/api/v1")
                ));
    }
}
