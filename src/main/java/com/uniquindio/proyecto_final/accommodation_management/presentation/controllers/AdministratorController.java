package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
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
@RequestMapping("/api/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdministratorDTO administrator, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(administrator));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AdministratorDTO administrator, BindingResult result){
        return create(administrator, result);
    }

    @GetMapping("/login")
    public ResponseEntity<AdministratorDTO> login(@RequestBody LoginDTO login){
        AdministratorDTO admin = service.login(login);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
