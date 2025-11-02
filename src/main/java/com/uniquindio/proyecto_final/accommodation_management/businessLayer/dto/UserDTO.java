package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    // Identificador único del usuario
    private int id;

    // Nombre del usuario
    private String name;

    // Apellido del usuario
    private String surname;

    // Correo electrónico del usuario (se usa para login)
    private String email;

    // Contraseña del usuario (en BD debe almacenarse encriptada)
    private String password;

    // Número de teléfono del usuario
    private String phone;

    // Fecha de nacimiento del usuario
    private LocalDate birthday;

    // URL de la imagen de perfil del usuario
    private String imgUrl;

    // Identificador del rol asociado (ej: HOST, USER, ADMIN)
    private Integer rolesId;

    // Identificador del departamento asociado al usuario
    private Integer departmentId;

    // Identificador de la ciudad asociada al usuario
    private Integer citiesId;

    // Fecha en la que se creó el registro en la base de datos
    private LocalDateTime createdAt;

    // Fecha de la última actualización del registro en BD
    private LocalDateTime updatedAt;

    // Indica si el usuario está activo o no (soft delete)
    private boolean active;

    // Nombre del departamento (para mostrar sin hacer otra consulta)
    private String departmentName;

    // Token JWT asignado al iniciar sesión
    private String token;

}