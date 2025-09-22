package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

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

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        boolean saved = service.save(user) != null;
        if(saved) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Usuario creado con éxito");
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no creado");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult result){
        return create(user, result);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody String email, @RequestBody String password, BindingResult result){
        return null;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserEntity> edit(@RequestBody UserEntity user, BindingResult result, @PathVariable Long idUser){
        return null;
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
