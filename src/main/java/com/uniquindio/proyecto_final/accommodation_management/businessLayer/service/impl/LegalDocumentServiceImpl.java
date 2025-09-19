package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.LegalDocumentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ImageDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.LegalDocumentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.LegalDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LegalDocumentServiceImpl implements LegalDocumentService {

    private final LegalDocumentDAO dao;

    public LegalDocumentServiceImpl(LegalDocumentDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public LegalDocumentDTO save(LegalDocumentDTO dto) {
        return dao.save(dto);
    }

}
