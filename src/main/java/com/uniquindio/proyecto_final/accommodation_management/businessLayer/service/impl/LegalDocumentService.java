package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentDTO;
import org.springframework.http.ResponseEntity;

public interface LegalDocumentService {

    ResponseEntity<LegalDocumentDTO> save(LegalDocumentDTO legalDocument);
}
