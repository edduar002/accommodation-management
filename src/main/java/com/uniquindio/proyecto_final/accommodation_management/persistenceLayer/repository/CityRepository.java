package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad CityEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para ciudades activas.
 */
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    /**
     * Obtiene todas las ciudades activas.
     * @return lista de ciudades activas.
     */
    // Consulta que selecciona todas las ciudades donde active = true
    @Query("SELECT a FROM CityEntity a WHERE a.active = true")
    List<CityEntity> allCities();

    /**
     * Obtiene todas las ciudades activas de un departamento específico.
     * @param departmentId ID del departamento.
     * @return lista de ciudades activas del departamento.
     */
    // Consulta que selecciona ciudades activas filtradas por departamento
    @Query("SELECT a FROM CityEntity a WHERE a.active = true AND a.departmentsId = :departmentId")
    List<CityEntity> citiesListDepartment(int departmentId);

}