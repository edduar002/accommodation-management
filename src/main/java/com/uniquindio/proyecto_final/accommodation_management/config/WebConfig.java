package com.uniquindio.proyecto_final.accommodation_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de Spring MVC para CORS (Cross-Origin Resource Sharing).
 *
 * <p>Permite que aplicaciones frontend, como Angular, puedan comunicarse con
 * la API backend sin restricciones de origen cruzado.</p>
 *
 * <p>Configuración principal:</p>
 * <ul>
 *     <li>Permite solicitudes desde http://localhost:4200.</li>
 *     <li>Permite métodos HTTP: GET, POST, PUT, DELETE y OPTIONS.</li>
 *     <li>Habilita el envío de credenciales (cookies, encabezados de autenticación).</li>
 * </ul>
 *
 * @since 1.0
 * @version 1.0
 */
//@Configuration
public class WebConfig {

    /**
     * Configura CORS para toda la aplicación.
     *
     * <p>Usa {@link WebMvcConfigurer} para definir los mappings y métodos
     * permitidos a nivel global.</p>
     *
     * @return un bean {@link WebMvcConfigurer} con la configuración de CORS
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("http://localhost:4200") // Permite Angular local
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true); // Permite envío de cookies y headers
            }
        };
    }
}