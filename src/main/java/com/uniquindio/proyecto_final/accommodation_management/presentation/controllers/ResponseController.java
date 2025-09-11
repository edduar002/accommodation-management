package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody ResponseDTO response){
        return service.save(response);
    }

}
