package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.FavoriteService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService service;

    @PostMapping("/create")
    public ResponseEntity<FavoriteDTO> create(@RequestBody FavoriteDTO administrator){
        return service.save(administrator);
    }

}
