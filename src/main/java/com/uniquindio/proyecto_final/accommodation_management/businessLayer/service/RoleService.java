package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO role);

    List<RoleDTO> rolesList();
}
