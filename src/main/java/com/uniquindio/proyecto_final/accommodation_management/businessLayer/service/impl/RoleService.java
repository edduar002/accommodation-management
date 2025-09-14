package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    RoleEntity save(RoleEntity role);
}
