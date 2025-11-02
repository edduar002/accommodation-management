package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ResponseCommentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link ResponseDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link ResponseRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link ResponseCommentMapper}.</p>
 *
 * <p>Incluye operaciones de creación de respuestas a comentarios y
 * recuperación de todas las respuestas de un host específico.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class ResponseCommentDAO {

    private final ResponseRepository responseCommentRepository;
    private final ResponseCommentMapper responseCommentMapper;

    /**
     * Persiste una respuesta a un comentario en la base de datos.
     *
     * @param dto DTO de la respuesta a guardar
     * @return DTO de la respuesta guardada
     */
    public ResponseDTO save(ResponseDTO dto) {
        ResponseCommentEntity entity = responseCommentMapper.toEntity(dto);
        ResponseCommentEntity savedEntity = responseCommentRepository.save(entity);
        return responseCommentMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene todas las respuestas asociadas a un host específico.
     *
     * @param idHost ID del host
     * @return Lista de {@link ResponseDTO} asociadas al host
     */
    public List<ResponseDTO> getByComment(int idHost) {
        List<ResponseCommentEntity> entities = responseCommentRepository.findAllResponses(idHost);
        return entities.stream()
                .map(responseCommentMapper::toDTO)
                .collect(Collectors.toList());
    }
}