package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.LegalDocumentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
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
@RequestMapping("/api/legaldocuments")
public class LegalDocumentController {

    @Autowired
    private LegalDocumentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LegalDocumentDTO document, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(document));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody LegalDocumentDTO document, BindingResult result){
        return create(document, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
