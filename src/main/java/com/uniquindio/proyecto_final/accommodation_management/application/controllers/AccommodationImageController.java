package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accomodationsimages")
public class AccommodationImageController {

    @Autowired
    private AccommodationImageService service;

    @PostMapping
    public ResponseEntity<AccommodationImage> create(@RequestBody AccommodationImage accommodationImage){
        return service.save(accommodationImage);
    }

}
