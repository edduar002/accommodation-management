package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @PostMapping("/create")
    public ResponseEntity<AdministratorDTO> create(@RequestBody AdministratorDTO administrator){
        return service.save(administrator);
    }

}
