package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FavoriteAccommodationServiceImpl implements FavoriteAccommodationService {
    @Override
    public ResponseEntity<FavoriteAccommodationEntity> save(FavoriteAccommodationEntity favoriteAccommodation) {
        return null;
    }
}
