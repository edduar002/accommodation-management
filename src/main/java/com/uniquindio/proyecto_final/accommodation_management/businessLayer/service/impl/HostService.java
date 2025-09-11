package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostDTO;
import org.springframework.http.ResponseEntity;

public interface HostService {

    ResponseEntity<HostDTO> save(HostDTO host);

    ResponseEntity<HostDTO> edit(int idHost);

    ResponseEntity<HostDTO> changePassword(int idHost);
}
