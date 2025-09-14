package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostRepository repository;

    @Override
    public ResponseEntity<HostEntity> save(HostEntity host) {
        return null;
    }

    @Override
    public ResponseEntity<HostEntity> edit(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<HostEntity> changePassword(int idHost) {
        return null;
    }
}
