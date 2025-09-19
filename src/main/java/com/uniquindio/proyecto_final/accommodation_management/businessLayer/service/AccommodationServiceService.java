package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;

public interface AccommodationServiceService {

    AccommodationServiceDTO save(AccommodationServiceDTO accommodationService);
}
