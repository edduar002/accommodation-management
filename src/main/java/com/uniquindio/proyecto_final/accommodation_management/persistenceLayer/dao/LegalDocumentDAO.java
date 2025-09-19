package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.LegalDocumentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.LegalDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalDocumentDAO {

    private final LegalDocumentRepository legalDocumentRepository;
    private final LegalDocumentMapper legalDocumentMapper;

    public LegalDocumentDTO save(LegalDocumentDTO dto) {
        LegalDocumentEntity entity = legalDocumentMapper.toEntity(dto);
        LegalDocumentEntity savedEntity = legalDocumentRepository.save(entity);
        return legalDocumentMapper.toDTO(savedEntity);
    }
}