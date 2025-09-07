package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Response;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService{

    @Autowired
    private ResponseRepository repository;

    @Override
    public ResponseEntity<Response> save(Response response) {
        return null;
    }
}
