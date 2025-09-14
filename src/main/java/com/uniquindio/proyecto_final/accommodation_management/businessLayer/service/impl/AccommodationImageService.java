package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import org.springframework.http.ResponseEntity;

public interface AccommodationImageService {

    ResponseEntity<AccommodationImageEntity> save(AccommodationImageEntity accommodationImage);
}
