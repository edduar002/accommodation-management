package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
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

    @GetMapping("/login")
    public ResponseEntity<HostDTO> login(@RequestBody LoginDTO login){
        HostDTO host = service.login(login);
        if (host == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(host);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody HostDTO host, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<HostDTO> hostOptional = service.edit(id, host);
        if(hostOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(hostOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable int id, @RequestBody ChangePasswordDTO dto) {
        Optional<HostDTO> hostOptional = service.changePassword(id, dto);
        if (hostOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(hostOptional.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "La contraseña actual es incorrecta");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @PutMapping("/recoveryPassword/{id}")
    public ResponseEntity<?> recoveryPassword(@PathVariable int id, @RequestBody RecoverPasswordDTO dto) {
        Optional<HostDTO> userOptional = service.recoveryPassword(id, dto.getNewPassword());
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "Usuario no encontrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
