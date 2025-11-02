package com.uniquindio.proyecto_final.accommodation_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI (Swagger) para documentar la API REST.
 *
 * <p>Define información general de la API, servidores disponibles, licencia,
 * contacto y esquema de seguridad para JWT.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *     <li>Proporcionar metadatos de la API: título, versión, descripción, contacto y licencia.</li>
 *     <li>Configurar los servidores disponibles en Swagger: desarrollo, pruebas y producción.</li>
 *     <li>Definir esquema de seguridad JWT (Bearer Authentication) para la API.</li>
 * </ul>
 *
 * <p>Esta clase permite que Swagger genere documentación interactiva de tu API
 * accesible desde el navegador.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Tienda Virtual API",
                version = "1.0.0",
                description = "API REST para la gestión completa de una tienda virtual",
                contact = @Contact(
                        name = "Equipo de Desarrollo",
                        email = "dev@tiendavirtual.com",
                        url = "https://tiendavirtual.com"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Servidor de Desarrollo"
                ),
                @Server(
                        url = "https://api-test.tiendavirtual.com",
                        description = "Servidor de Pruebas"
                ),
                @Server(
                        url = "https://api.tiendavirtual.com",
                        description = "Servidor de Producción"
                )
        }
)
public class OpenApiConfig {

    /**
     * Configura OpenAPI con componentes adicionales, incluyendo esquema de seguridad JWT.
     *
     * @return objeto {@link OpenAPI} configurado para la documentación de Swagger
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Ingresa tu token JWT")
                        )
                );
    }

}