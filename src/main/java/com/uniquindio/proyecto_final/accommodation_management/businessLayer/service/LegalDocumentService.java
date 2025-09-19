package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;

public interface LegalDocumentService {

    LegalDocumentDTO save(LegalDocumentDTO legalDocument);
}
