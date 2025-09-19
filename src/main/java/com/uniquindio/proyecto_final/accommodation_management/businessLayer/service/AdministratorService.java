package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;

public interface AdministratorService {
    AdministratorDTO save(AdministratorDTO administrator);
}
