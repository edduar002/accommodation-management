package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface HostService {

    HostEntity save(HostEntity host);

    Optional<HostEntity> edit(int idHost, HostEntity host);

    ResponseEntity<HostEntity> changePassword(int idHost);
}
