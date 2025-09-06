package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.respositories.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccommodationServiceServiceImpl implements AccommodationServiceService{

    @Autowired
    private AccommodationServiceRepository repository;

    @Override
    public ResponseEntity<AccommodationService> create(AccommodationService accommodationService) {
        return null;
    }
}
