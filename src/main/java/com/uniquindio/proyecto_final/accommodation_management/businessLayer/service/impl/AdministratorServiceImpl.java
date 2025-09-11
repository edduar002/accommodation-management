package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Override
    public ResponseEntity<AdministratorDTO> save(AdministratorDTO administrator) {
        return null;
    }
}
