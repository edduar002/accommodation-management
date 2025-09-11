package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository repository;

    @Override
    public ResponseEntity<CityDTO> save(CityDTO city) {
        return null;
    }
}
