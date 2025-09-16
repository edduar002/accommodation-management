package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository repository;

    @Override
    @Transactional
    public FavoriteEntity save(FavoriteEntity dto) {
        return repository.save(dto);
    }
}
