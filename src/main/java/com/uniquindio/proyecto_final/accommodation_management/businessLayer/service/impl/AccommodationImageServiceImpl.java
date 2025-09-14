package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccommodationImageServiceImpl implements AccommodationImageService {

    @Autowired
    private AccommodationImageRepository repository;

    @Override
    public ResponseEntity<AccommodationImageEntity> save(AccommodationImageEntity accommodationImage) {
        return null;
    }
}
