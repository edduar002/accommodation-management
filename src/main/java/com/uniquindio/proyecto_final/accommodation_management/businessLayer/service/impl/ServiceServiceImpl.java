package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repository;

    @Override
    public ResponseEntity<ServiceDTO> save(ServiceDTO service) {
        return null;
    }
}
