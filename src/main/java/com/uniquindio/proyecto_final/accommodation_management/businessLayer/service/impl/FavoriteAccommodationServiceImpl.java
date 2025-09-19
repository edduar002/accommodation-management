package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteAccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteAccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteAccommodationServiceImpl implements FavoriteAccommodationService {

    @Autowired
    private FavoriteAccommodationRepository repository;

    @Override
    @Transactional
    public FavoriteAccommodationEntity save(FavoriteAccommodationEntity dto) {
        return repository.save(dto);
    }
}
