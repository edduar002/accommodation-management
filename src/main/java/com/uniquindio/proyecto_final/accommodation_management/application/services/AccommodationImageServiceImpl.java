package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationImage;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.AccommodationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccommodationImageServiceImpl implements AccommodationImageService {

    @Autowired
    private AccommodationImageRepository repository;

    @Override
    public ResponseEntity<AccommodationImage> save(AccommodationImage accommodationImage) {
        return null;
    }
}
