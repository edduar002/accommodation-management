package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Host;
import org.springframework.http.ResponseEntity;

public interface HostService {

    ResponseEntity<Host> save(Host host);

    ResponseEntity<Host> edit(int idHost);

    ResponseEntity<Host> changePassword(int idHost);
}
