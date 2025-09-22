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

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberGuests;
    private String status;
    private Integer accommodationsId;
    private Integer hostsId;
    private Integer usersId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
