package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;

public interface RoleService {

    RoleDTO save(RoleDTO role);
}
