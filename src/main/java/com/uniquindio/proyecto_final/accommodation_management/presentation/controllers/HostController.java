package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private HostService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HostDTO host, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(host));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody HostDTO host, @Valid BindingResult result){
        return create(host, result);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody String email, @RequestBody String password, BindingResult result){
        return null;
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestParam int idHost, @RequestBody HostDTO host, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<HostDTO> hostOptional = service.edit(idHost, host);
        if(hostOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(hostOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changePassword")
    public ResponseEntity<HostDTO> changePassword(@RequestParam int idHost, BindingResult result){
        return service.changePassword(idHost);
    }

    @PostMapping("/recoveryPassword")
    public ResponseEntity<UserEntity> recoveryPassword(@RequestBody String email, @RequestBody String newPassword, BindingResult result){
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
