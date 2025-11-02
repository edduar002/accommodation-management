package com.uniquindio.proyecto_final.accommodation_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot de gestión de alojamientos.
 * Contiene el método main que inicia la aplicación.
 */
@SpringBootApplication
public class AccommodationManagementApplication {

	/**
	 * Método principal que arranca la aplicación Spring Boot.
	 * @param args argumentos de línea de comandos.
	 */
	// Inicia la aplicación usando SpringApplication
	public static void main(String[] args) {
		SpringApplication.run(AccommodationManagementApplication.class, args);
	}

}