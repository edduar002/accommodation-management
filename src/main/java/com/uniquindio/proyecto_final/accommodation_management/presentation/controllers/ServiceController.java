package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServiceDTO serviceEnt, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(serviceEnt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ServiceDTO serviceEnt, BindingResult result){
        return create(serviceEnt, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
