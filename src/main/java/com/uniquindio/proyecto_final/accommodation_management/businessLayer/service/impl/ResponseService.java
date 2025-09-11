package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseEntity;

public interface ResponseService {

    org.springframework.http.ResponseEntity<ResponseEntity> save(ResponseEntity response);
}
