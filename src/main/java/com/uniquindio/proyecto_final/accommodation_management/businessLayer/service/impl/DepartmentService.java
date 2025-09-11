package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import org.springframework.http.ResponseEntity;

public interface DepartmentService
{
    ResponseEntity<DepartmentEntity> save(DepartmentEntity department);
}
