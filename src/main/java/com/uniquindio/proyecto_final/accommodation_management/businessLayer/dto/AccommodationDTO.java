package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {

    // Identificador único del alojamiento
    private int id;

    // Descripción detallada del alojamiento
    private String detailedDescription;

    // Dirección general o referencia del alojamiento
    private String direction;

    // Ubicación exacta (coordenadas, link maps o referencia precisa)
    private String exactLocation;

    // Precio de alojamiento
    private Integer price;

    // Capacidad máxima de personas que pueden alojarse
    private Integer maximumCapacity;

    // Identificador del anfitrión que publica el alojamiento
    private Integer hostsId;

    // Disponibilidad actual del alojamiento (true = disponible)
    private boolean available;

    // Identificador del departamento al que pertenece el alojamiento
    private Integer departmentsId;

    // Identificador de la ciudad donde está el alojamiento
    private Integer citiesId;

    // Fecha y hora en que se registró el alojamiento
    private LocalDateTime createdAt;

    // Fecha y hora en que se actualizó por última vez la información
    private LocalDateTime updatedAt;

    // Estado lógico (true = activo / false = inactivo o eliminado)
    private boolean active;

    // Nombre del departamento (para visualización, no para persistencia)
    private String departmentName;

    // Nombre de la ciudad (para visualización)
    private String cityName;

    // Listado de servicios incluidos, generalmente como texto
    private String services;

    // URL o ruta de la imagen principal del alojamiento
    private String imgUrl;

}