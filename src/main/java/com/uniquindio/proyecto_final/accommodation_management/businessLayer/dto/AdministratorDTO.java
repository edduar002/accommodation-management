package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorDTO {

    // Identificador único del administrador
    private int id;

    // Nombre del administrador
    private String name;

    // Apellido del administrador
    private String surname;

    // Correo electrónico del administrador (usado para login)
    private String email;

    // Contraseña encriptada del administrador
    private String password;

    // Fecha y hora de creación del registro del administrador
    private LocalDateTime createdAt;

    // Fecha y hora de la última actualización del registro
    private LocalDateTime updatedAt;

    // Identificador del rol asociado al administrador
    private Integer rolesId;

    // Token de autenticación generado tras iniciar sesión
    private String token;

}