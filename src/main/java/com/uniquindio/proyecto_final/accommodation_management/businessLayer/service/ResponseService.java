package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;

import java.util.List;

/**
 * Servicio encargado de gestionar respuestas a comentarios de alojamientos.
 */
public interface ResponseService {

    /**
     * Persiste una respuesta a un comentario.
     * @param response DTO de la respuesta (no nulo)
     * @return DTO persistido
     */
    ResponseDTO save(ResponseDTO response);

    /**
     * Obtiene todas las respuestas asociadas a los comentarios de un host.
     * @param idHost id del host
     * @return lista de respuestas (posiblemente vacía)
     */
    List<ResponseDTO> getByComment(int idHost);
}