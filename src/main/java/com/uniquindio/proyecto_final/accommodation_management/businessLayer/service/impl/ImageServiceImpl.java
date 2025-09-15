package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository repository;

    @Override
    @Transactional
    public ImageEntity save(ImageEntity dto) {
        return repository.save(dto);
    }

}
