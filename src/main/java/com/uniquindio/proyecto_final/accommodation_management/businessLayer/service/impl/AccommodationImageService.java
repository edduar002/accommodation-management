package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageDTO;
import org.springframework.http.ResponseEntity;

public interface AccommodationImageService {

    ResponseEntity<AccommodationImageDTO> save(AccommodationImageDTO accommodationImage);
}
