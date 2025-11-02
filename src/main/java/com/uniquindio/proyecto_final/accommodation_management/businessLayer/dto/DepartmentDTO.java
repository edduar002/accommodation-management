package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    // Identificador único del departamento
    private int id;

    // Nombre del departamento
    private String name;

    // Fecha en la que se creó el registro en la base de datos
    private LocalDateTime createdAt;

    // Fecha en la que se actualizó el registro por última vez
    private LocalDateTime updatedAt;

    // Indica si el departamento está activo (true) o inactivo (false)
    private boolean active;

}