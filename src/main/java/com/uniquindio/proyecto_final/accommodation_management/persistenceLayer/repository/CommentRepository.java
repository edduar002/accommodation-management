package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad CommentEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para comentarios de alojamientos.
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Obtiene la lista de comentarios asociados a un alojamiento específico.
     * @param idAccommodation ID del alojamiento.
     * @return lista de comentarios del alojamiento.
     */
    // Consulta que selecciona comentarios filtrados por ID de alojamiento
    @Query("SELECT c FROM CommentEntity c WHERE c.accommodationsId = :idAccommodation")
    List<CommentEntity> commentsList(@Param("idAccommodation") int idAccommodation);

}