package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationImageServiceImpl implements AccommodationImageService {

    @Autowired
    private AccommodationImageRepository repository;

    @Override
    @Transactional
    public AccommodationImageEntity save(AccommodationImageEntity dto) {
        return repository.save(dto);
    }
}
