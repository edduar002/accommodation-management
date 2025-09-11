package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.UserService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user){
        return service.create(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user){
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestParam String email, @RequestParam String password){
        return service.login(email, password);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserEntity> edit(@RequestParam int idUser){
        return service.edit(idUser);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserEntity> changePassword(@RequestParam int idUser){
        return service.changePassword(idUser);
    }

}
