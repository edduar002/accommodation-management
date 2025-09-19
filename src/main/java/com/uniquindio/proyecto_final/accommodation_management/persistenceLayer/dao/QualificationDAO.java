package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.QualificationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QualificationDAO {

    private final QualificationRepository qualificationRepository;
    private final QualificationMapper qualificationMapper;

    public QualificationDTO save(QualificationDTO dto) {
        QualificationEntity entity = qualificationMapper.toEntity(dto);
        QualificationEntity savedEntity = qualificationRepository.save(entity);
        return qualificationMapper.toDTO(savedEntity);
    }
}