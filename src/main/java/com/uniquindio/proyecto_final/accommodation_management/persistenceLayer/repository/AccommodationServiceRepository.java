package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad AccommodationServiceEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
public interface AccommodationServiceRepository extends JpaRepository<AccommodationServiceEntity, Integer> {
    // No se requieren métodos adicionales; JpaRepository ya provee CRUD completo.
}