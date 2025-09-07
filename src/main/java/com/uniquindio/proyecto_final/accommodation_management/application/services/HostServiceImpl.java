package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Host;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostRepository repository;

    @Override
    public ResponseEntity<Host> save(Host host) {
        return null;
    }

    @Override
    public ResponseEntity<Host> edit(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<Host> changePassword(int idHost) {
        return null;
    }
}
