package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.LegalDocument;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.LegalDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LegalDocumentServiceImpl implements LegalDocumentService{

    @Autowired
    private LegalDocumentRepository repository;

    @Override
    public ResponseEntity<LegalDocument> save(LegalDocument legalDocument) {
        return null;
    }
}
