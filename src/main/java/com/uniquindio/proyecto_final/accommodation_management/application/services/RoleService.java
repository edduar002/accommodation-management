package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<Role> save(Role role);
}
