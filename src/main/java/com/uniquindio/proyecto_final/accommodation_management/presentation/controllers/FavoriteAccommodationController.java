package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.FavoriteAccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favoriteaccommodation")
public class FavoriteAccommodationController {

    @Autowired
    private FavoriteAccommodationService service;

    @PostMapping("/create")
    public ResponseEntity<FavoriteAccommodationDTO> create(@RequestBody FavoriteAccommodationDTO favoriteAccommodation){
        return service.save(favoriteAccommodation);
    }

}
