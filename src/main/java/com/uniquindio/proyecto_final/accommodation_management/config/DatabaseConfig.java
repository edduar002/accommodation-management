package com.uniquindio.proyecto_final.accommodation_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuración de la base de datos para el entorno de desarrollo.
 *
 * <p>Esta clase marca la configuración de Spring para cargar propiedades
 * de conexión a la base de datos desde un archivo externo
 * {@code application-dev.properties} ubicado en el classpath.</p>
 *
 * <p>Spring utiliza estas propiedades para inicializar la conexión a la base
 * de datos y otros beans relacionados con persistencia.</p>
 *
 * <p><b>Nota:</b> Para otros entornos (producción, pruebas), se pueden crear
 * clases similares con {@code @PropertySource} apuntando a distintos archivos
 * de propiedades.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:application-dev.properties")
public class DatabaseConfig {
    // Clase vacía: solo sirve para indicar a Spring la ubicación del archivo de propiedades
}