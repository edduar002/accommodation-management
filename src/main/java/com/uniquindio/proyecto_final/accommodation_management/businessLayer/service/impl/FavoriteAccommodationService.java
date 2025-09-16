package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import org.springframework.http.ResponseEntity;

public interface FavoriteAccommodationService {
    FavoriteAccommodationEntity save(FavoriteAccommodationEntity favoriteAccommodation);
}
