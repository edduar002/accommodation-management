package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.LegalDocument;
import org.springframework.http.ResponseEntity;

public interface LegalDocumentService {

    ResponseEntity<LegalDocument> save(LegalDocument legalDocument);
}
