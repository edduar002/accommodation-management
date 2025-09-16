package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteAccommodationRepository;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
