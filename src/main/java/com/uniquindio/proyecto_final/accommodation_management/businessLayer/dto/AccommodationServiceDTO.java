package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationServiceDTO {

    private int id;
    private int accommodationsId;
    private int servicesId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
