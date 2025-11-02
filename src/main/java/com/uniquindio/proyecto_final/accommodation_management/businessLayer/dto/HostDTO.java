package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostDTO {

    // Identificador único del anfitrión
    private int id;

    // Nombre del anfitrión
    private String name;

    // Apellido del anfitrión
    private String surname;

    // Correo electrónico del anfitrión
    private String email;

    // Contraseña del anfitrión (se almacena encriptada)
    private String password;

    // Número de teléfono del anfitrión
    private String phone;

    // Fecha de nacimiento del anfitrión
    private LocalDate birthday;

    // URL de la imagen de perfil del anfitrión
    private String imgUrl;

    // Identificador del rol asociado (ej: anfitrión, admin)
    private Integer rolesId;

    // Descripción personal que el anfitrión desea mostrar
    private String personalDescription;

    // Identificador del departamento donde reside el anfitrión
    private Integer departmentsId;

    // Identificador de la ciudad donde reside el anfitrión
    private Integer citiesId;

    // Fecha en que se creó el registro en la base de datos
    private LocalDateTime createdAt;

    // Fecha de la última actualización del registro
    private LocalDateTime updatedAt;

    // Indica si el anfitrión está activo en el sistema
    private boolean active;

    // Nombre del departamento (para mostrar sin necesidad de más consultas)
    private String departmentName;

    // Nombre de la ciudad (para mostrar sin necesidad de más consultas)
    private String cityName;

    // Token de autenticación (si corresponde)
    private String token;

}