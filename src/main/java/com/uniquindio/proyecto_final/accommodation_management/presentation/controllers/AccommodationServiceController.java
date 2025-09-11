package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/accomodationsservices")
public class AccommodationServiceController {

    @Autowired
    private AccommodationServiceService service;

    @PostMapping("/create")
    public ResponseEntity<AccommodationServiceDTO> create(@RequestBody AccommodationServiceDTO accommodationService){
        return service.create(accommodationService);
    }

}
