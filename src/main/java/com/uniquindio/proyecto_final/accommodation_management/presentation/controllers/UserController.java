package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email.EmailService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        // Guardar usuario
        UserDTO savedUser = service.save(user);

        // Enviar correo de bienvenida
        try {
            emailService.enviarCorreoBienvenida(savedUser.getEmail(), savedUser.getName());
        } catch (Exception e) {
            // Si el correo falla, solo lo logeamos, no bloqueamos el registro
            System.out.println("Error enviando correo: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<UserDTO> detail(@PathVariable("id") int accommodationId) {
        UserDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        UserDTO user = service.login(login);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody UserDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<UserDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> usersList(){
        List<UserDTO> todos = service.usersList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<UserDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable int id, @RequestBody ChangePasswordDTO dto) {
        Optional<UserDTO> userOptional = service.changePassword(id, dto);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "La contraseña actual es incorrecta");
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
