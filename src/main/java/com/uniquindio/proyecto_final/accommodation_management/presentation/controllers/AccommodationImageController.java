package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/accommodationsimages")
public class AccommodationImageController {

    @Autowired
    private AccommodationImageService service;

    @PostMapping("/create")
    public ResponseEntity<AccommodationImageDTO> create(@RequestBody AccommodationImageDTO accommodationImage){
        return service.save(accommodationImage);
    }

}
