package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

    ServiceEntity save(ServiceEntity service);
}
