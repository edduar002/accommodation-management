package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements QualificationService{

    @Autowired
    private QualificationRepository repository;

    @Override
    public ResponseEntity<QualificationDTO> save(QualificationDTO qualification) {
        return null;
    }
}
