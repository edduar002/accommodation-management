package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.business.respositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repository;

    @Override
    public ResponseEntity<com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Service> save(com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Service service) {
        return null;
    }
}
