package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    ResponseEntity<Image> save(Image image);
}
