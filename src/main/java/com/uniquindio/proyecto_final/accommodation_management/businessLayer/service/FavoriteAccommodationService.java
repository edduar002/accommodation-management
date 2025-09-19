package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;

public interface FavoriteAccommodationService {
    FavoriteAccommodationDTO save(FavoriteAccommodationDTO favoriteAccommodation);
}
