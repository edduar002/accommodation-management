package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository repository;

    @Override
    public ResponseEntity<ImageDTO> save(ImageDTO image) {
        return null;
    }
}
