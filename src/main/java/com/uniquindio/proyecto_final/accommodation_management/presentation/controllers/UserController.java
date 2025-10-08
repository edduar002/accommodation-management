package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult result){
        return create(user, result);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO login){
        UserDTO user = service.login(login);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
        public ResponseEntity<?> edit(@PathVariable int id, @RequestBody UserDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<UserDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserEntity> changePassword(@RequestBody String oldPassword, @RequestBody String newPassword, @PathVariable Long idUser){
        return null;
    }

    @PostMapping("/recoveryPassword")
    public ResponseEntity<UserEntity> recoveryPassword(@RequestBody String email, @RequestBody String newPassword){
        return null;
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
