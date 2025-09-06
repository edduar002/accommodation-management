package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Response;
import org.springframework.http.ResponseEntity;

public interface ResponseService {

    ResponseEntity<Response> save(Response response);
}
