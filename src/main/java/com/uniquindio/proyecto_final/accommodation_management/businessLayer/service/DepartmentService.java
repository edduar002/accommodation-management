package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService
{
    DepartmentDTO save(DepartmentDTO department);

    List<DepartmentDTO> departmentsList();

    DepartmentDTO detail(int accommodation);

    Optional<DepartmentDTO> edit(int idAccommodation, DepartmentDTO user);
}
