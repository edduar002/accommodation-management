package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer numberGuests;
    private String state;
    private Integer accommodationsId;
    private Integer hostsId;
    private Integer usersId;
    private Integer calification;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
