package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Department;
import org.springframework.http.ResponseEntity;

public interface DepartmentService
{
    ResponseEntity<Department> save(Department department);
}
