package com.uniquindio.proyecto_final.accommodation_management.businessLayer.exceptions;

// Excepción para manejar errores de lógica o reglas de negocio
public class BusinessException extends RuntimeException {

    // Identificador recomendado para clases que extienden Throwable
    private static final long serialVersionUID = 1L;

    // Constructor que recibe un mensaje descriptivo del error
    public BusinessException(String message) {
        super(message);
    }

}