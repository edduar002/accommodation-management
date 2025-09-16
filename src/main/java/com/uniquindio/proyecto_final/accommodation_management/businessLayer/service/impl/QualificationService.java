package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QualificationService {

    QualificationEntity save(QualificationEntity qualification);

    ResponseEntity<List<QualificationEntity>> averageGrades(int idAccommodation);
}
