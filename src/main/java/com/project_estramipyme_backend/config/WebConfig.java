package com.project_estramipyme_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*  Este código configura CORS en la aplicación Spring Boot para permitir que el frontend ejecutándose en el puerto 4200
pueda comunicarse con el backend sin problemas de seguridad.*/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
    }
}
