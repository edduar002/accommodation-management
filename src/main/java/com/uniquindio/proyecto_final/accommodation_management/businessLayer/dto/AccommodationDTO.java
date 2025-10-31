package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {

    private int id;
    private String detailedDescription;
    private String direction;
    private String exactLocation;
    private Integer price;
    private Integer maximumCapacity;
    private Integer hostsId;
    private boolean available;
    private Integer departmentsId;
    private Integer citiesId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
    private String departmentName;
    private String cityName;
    private String services;
    private String imgUrl;
}
