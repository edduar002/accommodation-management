package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteDTO;
import org.springframework.http.ResponseEntity;

public interface FavoriteService {
    ResponseEntity<FavoriteDTO> save(FavoriteDTO administrator);
}
