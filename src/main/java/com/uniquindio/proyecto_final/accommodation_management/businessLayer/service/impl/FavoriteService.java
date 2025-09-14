package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import org.springframework.http.ResponseEntity;

public interface FavoriteService {
    ResponseEntity<FavoriteEntity> save(FavoriteEntity administrator);
}
