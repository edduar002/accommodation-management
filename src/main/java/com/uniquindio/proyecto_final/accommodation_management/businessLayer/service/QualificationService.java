package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;

public interface QualificationService {

    QualificationDTO save(QualificationDTO qualification);
}
