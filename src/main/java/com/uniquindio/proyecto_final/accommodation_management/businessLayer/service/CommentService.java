package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import java.util.List;

/**
 * Servicio encargado de gestionar los comentarios de los usuarios
 * sobre los alojamientos y sus respuestas.
 */
public interface CommentService {

    /**
     * Guarda un nuevo comentario en el sistema.
     *
     * @param comment DTO del comentario a guardar.
     * @return DTO persistido con su ID asignado.
     */
    CommentDTO save(CommentDTO comment);

    /**
     * Lista los comentarios de un alojamiento específico.
     *
     * @param idAccommodation ID del alojamiento.
     * @return Lista de DTOs de comentarios asociados.
     */
    List<CommentDTO> commentsList(int idAccommodation);

    /**
     * Permite responder un comentario existente.
     *
     * @param idComent ID del comentario a responder.
     * @param comment DTO con la respuesta o actualización del comentario.
     * @return DTO del comentario actualizado o la respuesta agregada.
     */
    CommentDTO respondComments(int idComent, CommentDTO comment);
}