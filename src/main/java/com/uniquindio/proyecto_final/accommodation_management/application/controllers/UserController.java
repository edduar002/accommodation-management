package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.UserService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Image;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return service.create(user);
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user){
        return service.register(user);
    }

    @PostMapping
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
        return service.login(email, password);
    }

    @PutMapping
    public ResponseEntity<User> edit(@RequestParam int idUser){
        return service.edit(idUser);
    }

    @PutMapping
    public ResponseEntity<User> changePassword(@RequestParam int idUser){
        return service.changePassword(idUser);
    }

}
