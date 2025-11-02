package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    // Correo electrónico utilizado para iniciar sesión
    private String email;

    // Contraseña ingresada por el usuario (se compara encriptada)
    private String password;

}