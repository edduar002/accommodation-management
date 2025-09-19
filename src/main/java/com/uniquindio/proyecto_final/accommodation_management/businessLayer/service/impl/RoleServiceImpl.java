package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.RoleService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.RoleDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO dao;

    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        return dao.save(dto);
    }

}
