package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import org.springframework.http.ResponseEntity;

public interface CityService {

    ResponseEntity<CityEntity> save(CityEntity city);
}
