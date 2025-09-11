package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.CityService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping("/create")
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO city){
        return service.save(city);
    }

}
