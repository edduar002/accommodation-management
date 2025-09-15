package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repository;

    @Override
    @Transactional
    public ServiceEntity save(ServiceEntity dto) {
        return repository.save(dto);
    }
}
