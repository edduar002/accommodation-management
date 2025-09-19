package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationServiceServiceImpl implements AccommodationServiceService {

    @Autowired
    private AccommodationServiceRepository repository;

    @Override
    @Transactional
    public AccommodationServiceEntity save(AccommodationServiceEntity dto) {
        return repository.save(dto);
    }
}
