package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;

public interface CityService {

    CityDTO save(CityDTO city);
}
