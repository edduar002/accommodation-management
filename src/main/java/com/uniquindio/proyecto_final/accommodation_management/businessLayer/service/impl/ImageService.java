package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageDTO;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    ResponseEntity<ImageDTO> save(ImageDTO image);
}
