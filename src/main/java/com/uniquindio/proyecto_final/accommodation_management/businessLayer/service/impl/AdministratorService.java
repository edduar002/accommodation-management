package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorDTO;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    ResponseEntity<AdministratorDTO> save(AdministratorDTO administrator);
}
