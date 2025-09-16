package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.LegalDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LegalDocumentServiceImpl implements LegalDocumentService{

    @Autowired
    private LegalDocumentRepository repository;

    @Override
    @Transactional
    public LegalDocumentEntity save(LegalDocumentEntity dto) {
        return repository.save(dto);
    }
}
