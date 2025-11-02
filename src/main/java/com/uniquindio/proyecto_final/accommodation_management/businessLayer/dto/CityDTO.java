package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    // Identificador único de la ciudad
    private int id;

    // Nombre de la ciudad
    private String name;

    // Identificador del departamento al que pertenece la ciudad
    private int departmentsId;

    // Fecha en la que se creó el registro en la base de datos
    private LocalDateTime createdAt;

    // Fecha de la última actualización del registro
    private LocalDateTime updatedAt;

    // Indica si la ciudad está activa (true) o inactiva (false)
    private boolean active;

    // Nombre del departamento (cuando se necesite mostrar información por relación)
    private String departmentName;

}