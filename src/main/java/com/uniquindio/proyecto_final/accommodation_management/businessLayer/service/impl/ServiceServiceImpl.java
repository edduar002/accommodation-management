package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.RoleDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ServiceDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceDAO dao;

    public ServiceServiceImpl(ServiceDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public ServiceDTO save(ServiceDTO dto) {
        return dao.save(dto);
    }
}
