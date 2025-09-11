package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.LegalDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LegalDocumentServiceImpl implements LegalDocumentService{

    @Autowired
    private LegalDocumentRepository repository;

    @Override
    public ResponseEntity<LegalDocumentDTO> save(LegalDocumentDTO legalDocument) {
        return null;
    }
}
