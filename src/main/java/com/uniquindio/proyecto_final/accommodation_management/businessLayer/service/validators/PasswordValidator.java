package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.validators;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.exceptions.BusinessException;

public class PasswordValidator {

    /**
     * Valida que la contraseña cumpla los requisitos mínimos de seguridad.
     *
     * @param password Contraseña a validar.
     * @throws BusinessException Si la contraseña no cumple con alguna de las reglas.
     */
    public static void validate(String password) {

        // Verifica que la contraseña no sea nula y tenga mínimo 6 caracteres
        if (password == null || password.length() < 6)
            throw new BusinessException("La contraseña debe tener al menos 6 caracteres.");

        // Verifica que contenga al menos una letra mayúscula
        if (!password.matches(".*[A-Z].*"))
            throw new BusinessException("La contraseña debe contener al menos una letra mayúscula.");

        // Verifica que contenga al menos una letra minúscula
        if (!password.matches(".*[a-z].*"))
            throw new BusinessException("La contraseña debe contener al menos una letra minúscula.");

        // Verifica que contenga al menos un número
        if (!password.matches(".*\\d.*"))
            throw new BusinessException("La contraseña debe contener al menos un número.");

        // Verifica que contenga al menos un carácter especial
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))
            throw new BusinessException("La contraseña debe contener al menos un carácter especial.");
    }
}