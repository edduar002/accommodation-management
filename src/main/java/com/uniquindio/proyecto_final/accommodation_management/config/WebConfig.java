package com.uniquindio.proyecto_final.accommodation_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**") // 🔹 Aplica a todos los endpoints
                        .allowedOrigins("http://localhost:4200") // 🔹 Permite Angular en dev
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 🔹 Métodos permitidos
                        .allowedHeaders("*") // 🔹 Headers permitidos
                        .allowCredentials(true); // 🔹 Permite enviar cookies o tokens si los usas
            }
        };
    }
}