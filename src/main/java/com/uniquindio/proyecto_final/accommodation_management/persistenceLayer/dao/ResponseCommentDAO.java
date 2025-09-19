package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ResponseCommentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResponseCommentDAO {

    private final ResponseRepository responseCommentRepository;
    private final ResponseCommentMapper responseCommentMapper;

    public ResponseDTO save(ResponseDTO dto) {
        ResponseComentEntity entity = responseCommentMapper.toEntity(dto);
        ResponseComentEntity savedEntity = responseCommentRepository.save(entity);
        return responseCommentMapper.toDTO(savedEntity);
    }
}