package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccommodationServiceServiceImpl implements com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationServiceService {

    @Autowired
    private AccommodationServiceRepository repository;

    @Override
    public ResponseEntity<AccommodationServiceEntity> create(AccommodationServiceEntity accommodationService) {
        return null;
    }
}
