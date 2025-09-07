package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;      // Para documentar cada método
import io.swagger.v3.oas.annotations.Parameter;      // Para documentar parámetros
import io.swagger.v3.oas.annotations.responses.ApiResponse;      // Para documentar respuestas
import io.swagger.v3.oas.annotations.responses.ApiResponses;     // Para múltiples respuestas
import io.swagger.v3.oas.annotations.tags.Tag;       // Para agrupar endpoints

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
