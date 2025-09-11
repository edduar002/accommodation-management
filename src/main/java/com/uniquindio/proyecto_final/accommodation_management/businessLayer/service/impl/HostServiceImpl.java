package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostRepository repository;

    @Override
    public ResponseEntity<HostDTO> save(HostDTO host) {
        return null;
    }

    @Override
    public ResponseEntity<HostDTO> edit(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<HostDTO> changePassword(int idHost) {
        return null;
    }
}
