package com.uniquindio.proyecto_final.accommodation_management.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Cloudinary para la aplicación.
 *
 * <p>Esta clase define un {@link Bean} de {@link Cloudinary} que se puede inyectar
 * en cualquier componente de Spring mediante {@code @Autowired}. Permite realizar
 * operaciones como subir, eliminar o transformar imágenes de manera programática.</p>
 *
 * <p><b>Nota de seguridad:</b> Las credenciales están actualmente hardcodeadas.
 * Se recomienda usar variables de entorno o {@code application.properties} para
 * evitar exponer información sensible en el código fuente.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 */
@Configuration
public class CloudinaryConfig {

    /**
     * Crea y configura un {@link Cloudinary} con los parámetros de conexión
     * a la cuenta específica.
     *
     * <p>Actualmente se usa:</p>
     * <ul>
     *     <li>cloud_name: "daeixho4x"</li>
     *     <li>api_key: "356184896876497"</li>
     *     <li>api_secret: "C4HKwYZF4WgBuAAmWH6mulrl68I"</li>
     * </ul>
     *
     * <p>Este bean puede inyectarse en cualquier servicio o componente que necesite
     * interactuar con Cloudinary.</p>
     *
     * @return instancia de {@link Cloudinary} lista para usarse
     */
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "daeixho4x",
                "api_key", "356184896876497",
                "api_secret", "C4HKwYZF4WgBuAAmWH6mulrl68I"
        ));
    }
}