package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad AdministratorEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y métodos de búsqueda personalizados.
 */
public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Integer> {

    /**
     * Busca un administrador por su correo electrónico.
     * @param email correo electrónico del administrador.
     * @return entidad AdministratorEntity correspondiente o null si no existe.
     */
    // Método derivado de Spring Data para buscar administrador por email
    AdministratorEntity findByEmail(String email);

}