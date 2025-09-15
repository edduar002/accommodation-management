package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    ImageEntity save(ImageEntity image);
}
