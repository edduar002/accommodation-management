package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;

public interface AccommodationImageService {

    AccommodationImageDTO save(AccommodationImageDTO accommodationImage);
}
