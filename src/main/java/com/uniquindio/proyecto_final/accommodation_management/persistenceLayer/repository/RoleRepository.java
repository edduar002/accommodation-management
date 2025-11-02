package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad RoleEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para roles activos.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    /**
     * Obtiene todos los roles activos.
     * @return lista de roles activos.
     */
    // Consulta que selecciona todos los roles donde active = true
    @Query("SELECT a FROM RoleEntity a WHERE a.active = true")
    List<RoleEntity> allRoles();

}