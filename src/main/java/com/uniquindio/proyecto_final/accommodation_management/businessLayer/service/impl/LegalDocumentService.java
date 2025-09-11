package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
import org.springframework.http.ResponseEntity;

public interface LegalDocumentService {

    ResponseEntity<LegalDocumentEntity> save(LegalDocumentEntity legalDocument);
}
