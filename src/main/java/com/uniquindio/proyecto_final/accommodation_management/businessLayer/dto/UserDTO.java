package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthday;
    private String imgUrl;
    private Integer rolesId;
    private Integer departmentId;
    private Integer citiesId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

}
