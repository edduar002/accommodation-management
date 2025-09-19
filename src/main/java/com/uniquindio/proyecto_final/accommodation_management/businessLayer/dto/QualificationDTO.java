package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDTO {

    private int id;
    private int numberStars;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
