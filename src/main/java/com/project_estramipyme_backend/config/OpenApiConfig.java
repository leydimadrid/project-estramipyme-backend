package com.project_estramipyme_backend.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server productionServer = new Server()
                .url("https://project-estramipyme-backend-production-44d9.up.railway.app")
                .description("Production Server - Railway");


        Server localServer = new Server()
                .url("http://localhost:8081")
                .description("Local Server - Development");


        return new OpenAPI()

                .info(new Info()
                        .title("Estramipyme API")
                        .version("1.0")
                        .description("Enterprise Assessment API"))
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .servers(Arrays.asList(productionServer, localServer)); // Agregar los servidores
    }


}