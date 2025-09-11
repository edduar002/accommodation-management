package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<RoleDTO> save(RoleDTO role);
}
