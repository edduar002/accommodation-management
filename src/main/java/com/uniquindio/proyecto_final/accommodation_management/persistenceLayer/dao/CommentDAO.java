package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.CommentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentDTO save(CommentDTO dto) {
        CommentEntity entity = commentMapper.toEntity(dto);
        CommentEntity savedEntity = commentRepository.save(entity);
        return commentMapper.toDTO(savedEntity);
    }

    public CommentDTO commentsList(CommentDTO dto) {
        CommentEntity entity = commentMapper.toEntity(dto);
        CommentEntity savedEntity = commentRepository.save(entity);
        return commentMapper.toDTO(savedEntity);
    }

}