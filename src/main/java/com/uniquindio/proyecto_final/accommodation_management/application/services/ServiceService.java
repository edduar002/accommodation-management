package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Service;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

    ResponseEntity<Service> save(Service service);
}
