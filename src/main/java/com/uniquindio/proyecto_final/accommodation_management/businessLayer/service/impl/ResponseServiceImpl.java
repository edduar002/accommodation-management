package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService{

    @Autowired
    private ResponseRepository repository;

    @Override
    public org.springframework.http.ResponseEntity<ResponseEntity> save(ResponseEntity response) {
        return null;
    }
}
