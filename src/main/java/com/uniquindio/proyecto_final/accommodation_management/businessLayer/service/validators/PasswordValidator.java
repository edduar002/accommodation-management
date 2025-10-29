package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.validators;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.exceptions.BusinessException;

public class PasswordValidator {

    public static void validate(String password) {
        if (password == null || password.length() < 6)
            throw new BusinessException("La contraseña debe tener al menos 6 caracteres.");

        if (!password.matches(".*[A-Z].*"))
            throw new BusinessException("La contraseña debe contener al menos una letra mayúscula.");

        if (!password.matches(".*[a-z].*"))
            throw new BusinessException("La contraseña debe contener al menos una letra minúscula.");

        if (!password.matches(".*\\d.*"))
            throw new BusinessException("La contraseña debe contener al menos un número.");

        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))
            throw new BusinessException("La contraseña debe contener al menos un carácter especial.");
    }
}