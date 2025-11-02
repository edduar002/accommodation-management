package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    // Identificador único del rol
    private int id;

    // Nombre del rol (ej: ADMIN, HOST, USER)
    private String name;

    // Fecha en que el rol fue creado en el sistema
    private LocalDateTime createdAt;

    // Fecha de última actualización del registro
    private LocalDateTime updatedAt;

    // Indica si el rol está activo o no (soft delete)
    private boolean active;

}