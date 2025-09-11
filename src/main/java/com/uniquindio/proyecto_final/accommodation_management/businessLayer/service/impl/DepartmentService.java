package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentDTO;
import org.springframework.http.ResponseEntity;

public interface DepartmentService
{
    ResponseEntity<DepartmentDTO> save(DepartmentDTO department);
}
