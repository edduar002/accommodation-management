package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService{

    @Autowired
    private QualificationRepository repository;

    @Override
    @Transactional
    public QualificationEntity save(QualificationEntity dto) {
        return repository.save(dto);
    }

    @Override
    public ResponseEntity<List<QualificationEntity>> averageGrades(int idAccommodation) {
        return null;
    }
}
