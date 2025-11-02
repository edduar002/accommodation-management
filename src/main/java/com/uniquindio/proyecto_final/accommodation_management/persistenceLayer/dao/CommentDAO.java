package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.CommentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link CommentDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link CommentRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link CommentMapper}.</p>
 *
 * <p>Incluye operaciones de guardado y listado de comentarios.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    /**
     * Persiste un {@link CommentDTO} en la base de datos.
     *
     * @param dto DTO del comentario a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public CommentDTO save(CommentDTO dto) {
        CommentEntity entity = commentMapper.toEntity(dto);
        CommentEntity savedEntity = commentRepository.save(entity);
        return commentMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene la lista de comentarios asociados a un alojamiento.
     *
     * @param idAccommodation identificador del alojamiento
     * @return lista de {@link CommentDTO} relacionados con el alojamiento
     */
    public List<CommentDTO> commentsList(int idAccommodation) {
        List<CommentEntity> entities = commentRepository.commentsList(idAccommodation);
        return entities.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}