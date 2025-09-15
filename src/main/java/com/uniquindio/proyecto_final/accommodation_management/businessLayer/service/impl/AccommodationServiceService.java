package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import org.springframework.http.ResponseEntity;

public interface AccommodationServiceService {

    AccommodationServiceEntity save(AccommodationServiceEntity accommodationService);
}
