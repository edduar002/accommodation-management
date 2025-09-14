package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.http.ResponseEntity;

public interface HostService {

    ResponseEntity<HostEntity> save(HostEntity host);

    ResponseEntity<HostEntity> edit(int idHost);

    ResponseEntity<HostEntity> changePassword(int idHost);
}
