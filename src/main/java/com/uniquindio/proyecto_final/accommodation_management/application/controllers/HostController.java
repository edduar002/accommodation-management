package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;      // Para documentar cada método
import io.swagger.v3.oas.annotations.Parameter;      // Para documentar parámetros
import io.swagger.v3.oas.annotations.responses.ApiResponse;      // Para documentar respuestas
import io.swagger.v3.oas.annotations.responses.ApiResponses;     // Para múltiples respuestas
import io.swagger.v3.oas.annotations.tags.Tag;       // Para agrupar endpoints

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private HostService service;

    @PostMapping
    public ResponseEntity<Host> create(@RequestBody Host host){
        return service.save(host);
    }

    @PutMapping
    public ResponseEntity<Host> edit(@RequestParam int idHost){
        return service.edit(idHost);
    }

    @PutMapping
    public ResponseEntity<Host> changePassword(@RequestParam int idHost){
        return service.changePassword(idHost);
    }

}
