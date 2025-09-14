package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    AdministratorEntity save(AdministratorEntity administrator);
}
