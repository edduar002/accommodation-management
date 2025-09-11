package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/create")
    public ResponseEntity<ImageDTO> create(@RequestBody ImageDTO image){
        return service.save(image);
    }

}
