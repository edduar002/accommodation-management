package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;

public interface DepartmentService
{
    DepartmentDTO save(DepartmentDTO department);
}
