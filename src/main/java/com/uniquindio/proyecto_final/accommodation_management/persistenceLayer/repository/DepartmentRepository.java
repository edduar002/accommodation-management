package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad DepartmentEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para departamentos activos.
 */
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    /**
     * Obtiene todos los departamentos activos.
     * @return lista de departamentos activos.
     */
    // Consulta que selecciona todos los departamentos donde active = true
    @Query("SELECT a FROM DepartmentEntity a WHERE a.active = true")
    List<DepartmentEntity> allDepartments();

}