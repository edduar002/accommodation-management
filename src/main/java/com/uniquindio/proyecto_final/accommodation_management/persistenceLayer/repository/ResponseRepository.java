package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad ResponseCommentEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para respuestas a comentarios.
 */
public interface ResponseRepository extends JpaRepository<ResponseCommentEntity, Integer> {

    /**
     * Obtiene todas las respuestas asociadas a un comentario específico.
     * @param idComment ID del comentario.
     * @return lista de respuestas del comentario.
     */
    // Consulta que selecciona todas las respuestas filtradas por ID de comentario
    @Query("SELECT r FROM ResponseCommentEntity r WHERE r.commentsId = :idComment")
    List<ResponseCommentEntity> findAllResponses(@Param("idComment") int idComment);

}