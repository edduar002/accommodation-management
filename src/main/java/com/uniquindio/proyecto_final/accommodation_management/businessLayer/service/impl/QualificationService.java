package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationDTO;
import org.springframework.http.ResponseEntity;

public interface QualificationService {

    ResponseEntity<QualificationDTO> save(QualificationDTO qualification);
}
