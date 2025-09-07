package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.QualificationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;      // Para documentar cada método
import io.swagger.v3.oas.annotations.Parameter;      // Para documentar parámetros
import io.swagger.v3.oas.annotations.responses.ApiResponse;      // Para documentar respuestas
import io.swagger.v3.oas.annotations.responses.ApiResponses;     // Para múltiples respuestas
import io.swagger.v3.oas.annotations.tags.Tag;       // Para agrupar endpoints

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {

    @Autowired
    private QualificationService service;

    @PostMapping
    public ResponseEntity<Qualification> create(@RequestBody Qualification qualification){
        return service.save(qualification);
    }

}
