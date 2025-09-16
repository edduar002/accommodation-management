package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ResponseService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
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

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ResponseComentEntity response, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ResponseComentEntity response, BindingResult result){
        return create(response, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
