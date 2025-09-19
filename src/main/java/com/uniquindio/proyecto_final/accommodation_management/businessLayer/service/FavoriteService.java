package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;

public interface FavoriteService {
    FavoriteDTO save(FavoriteDTO administrator);
}
