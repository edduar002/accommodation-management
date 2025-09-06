package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.City;
import org.springframework.http.ResponseEntity;

public interface CityService {

    ResponseEntity<City> save(City city);
}
