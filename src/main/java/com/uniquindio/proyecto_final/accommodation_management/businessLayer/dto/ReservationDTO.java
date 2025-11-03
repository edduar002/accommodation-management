package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    // Identificador único de la reserva
    private int id;

    // Fecha de entrada del huésped
    private LocalDate checkIn;

    // Fecha de salida del huésped
    private LocalDate checkOut;

    // Cantidad de huéspedes incluidos en la reserva
    private Integer numberGuests;

    // Estado actual de la reserva (ej: PENDIENTE, APROBADA, CANCELADA)
    private String state;

    // Identificador del alojamiento reservado
    private Integer accommodationsId;

    // Identificador del anfitrión dueño del alojamiento
    private Integer hostsId;

    // Identificador del usuario que realiza la reserva
    private Integer usersId;

    // Calificación del huésped hacia la experiencia (si existe)
    private Integer calification;

    // Fecha en la que se creó la reserva en el sistema
    private LocalDateTime createdAt;

    // Fecha de la última actualización de la reserva
    private LocalDateTime updatedAt;

    // URL o ruta de la imagen principal del alojamiento
    private String imgUrl;

    // Nombre de la ciudad (para visualización)
    private String cityName;

    // Descripción detallada del alojamiento
    private String detailedDescription;

}