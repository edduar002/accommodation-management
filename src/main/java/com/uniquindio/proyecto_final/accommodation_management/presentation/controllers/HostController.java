package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private HostService service;

    @PostMapping("/create")
    public ResponseEntity<HostEntity> create(@RequestBody HostEntity host){
        return service.save(host);
    }

    @PutMapping("/edit")
    public ResponseEntity<HostEntity> edit(@RequestParam int idHost){
        return service.edit(idHost);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<HostEntity> changePassword(@RequestParam int idHost){
        return service.changePassword(idHost);
    }

}
