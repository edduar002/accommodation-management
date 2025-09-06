package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import org.springframework.http.ResponseEntity;

public interface QualificationService {

    ResponseEntity<Qualification> save(Qualification qualification);
}
