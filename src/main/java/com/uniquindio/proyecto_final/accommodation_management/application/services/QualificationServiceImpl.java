package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements QualificationService{

    @Autowired
    private QualificationRepository repository;

    @Override
    public ResponseEntity<Qualification> save(Qualification qualification) {
        return null;
    }
}
