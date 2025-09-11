package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceDTO;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

    ResponseEntity<ServiceDTO> save(ServiceDTO service);
}
