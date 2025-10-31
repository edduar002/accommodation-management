package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email.EmailService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdministratorDTO administrator, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(administrator));
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable int id, @RequestBody ChangePasswordDTO dto) {
        Optional<AdministratorDTO> userOptional = service.changePassword(id, dto);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "La contraseña actual es incorrecta");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody AdministratorDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<AdministratorDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<AdministratorDTO> detail(@PathVariable("id") int accommodationId) {
        AdministratorDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AdministratorDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        // Guardar usuario
        AdministratorDTO savedUser = service.save(user);

        // Enviar correo de bienvenida
        try {
            emailService.enviarCorreoBienvenida(savedUser.getEmail(), savedUser.getName());
        } catch (Exception e) {
            // Si el correo falla, solo lo logeamos, no bloqueamos el registro
            System.out.println("Error enviando correo: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        AdministratorDTO admin = service.login(login);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
