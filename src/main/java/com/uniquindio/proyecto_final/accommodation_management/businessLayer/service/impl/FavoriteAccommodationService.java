package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationDTO;
import org.springframework.http.ResponseEntity;

public interface FavoriteAccommodationService {
    ResponseEntity<FavoriteAccommodationDTO> save(FavoriteAccommodationDTO favoriteAccommodation);
}
