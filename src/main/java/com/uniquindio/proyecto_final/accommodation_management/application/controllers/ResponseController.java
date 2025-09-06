package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.ResponseService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Image;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService service;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Response response){
        return service.save(response);
    }

}
