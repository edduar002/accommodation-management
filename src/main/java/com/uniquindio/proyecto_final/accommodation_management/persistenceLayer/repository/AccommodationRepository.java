package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad AccommodationEntity.
 * Proporciona métodos para operaciones CRUD y consultas personalizadas.
 */
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Integer> {

    /**
     * Busca todos los alojamientos activos.
     * @return lista de alojamientos activos.
     */
    // Consulta que selecciona alojamientos donde active = true
    @Query("SELECT a FROM AccommodationEntity a WHERE a.active = true")
    List<AccommodationEntity> searchAvailableAccommodations();

    /**
     * Obtiene la lista de alojamientos de un host específico.
     * @param idHost ID del host.
     * @return lista de alojamientos del host.
     */
    // Consulta que selecciona alojamientos pertenecientes a un host específico
    @Query("SELECT a FROM AccommodationEntity a WHERE a.hostsId = :idHost")
    List<AccommodationEntity> ownAccommodationList(@Param("idHost") int idHost);
}