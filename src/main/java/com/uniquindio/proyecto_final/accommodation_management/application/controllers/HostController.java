package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Department;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Host;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
