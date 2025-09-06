package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationImage;
import org.springframework.http.ResponseEntity;

public interface AccommodationImageService {

    ResponseEntity<AccommodationImage> save(AccommodationImage accommodationImage);
}
