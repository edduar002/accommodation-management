package com.uniquindio.proyecto_final.accommodation_management.business.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
