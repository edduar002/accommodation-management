package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationServiceService {

    ResponseEntity<AccommodationService> create(AccommodationService accommodationService);
}
