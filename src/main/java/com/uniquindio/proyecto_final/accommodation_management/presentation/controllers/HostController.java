package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private HostService service;

    @PostMapping("/create")
    public ResponseEntity<HostEntity> create(@RequestBody HostEntity host, BindingResult result){
        return service.save(host);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody String email, @RequestBody String password, BindingResult result){
        return null;
    }

    @PutMapping("/edit")
    public ResponseEntity<HostEntity> edit(@RequestParam int idHost, BindingResult result){
        return service.edit(idHost);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<HostEntity> changePassword(@RequestParam int idHost, BindingResult result){
        return service.changePassword(idHost);
    }

    @PostMapping("/recoveryPassword")
    public ResponseEntity<UserEntity> recoveryPassword(@RequestBody String email, @RequestBody String newPassword, BindingResult result){
        return null;
    }

}
