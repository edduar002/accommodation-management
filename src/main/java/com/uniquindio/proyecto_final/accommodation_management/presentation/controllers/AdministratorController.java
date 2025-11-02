package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para la entidad Administrator.
 * Proporciona endpoints para crear, registrar, editar, cambiar contraseña, login y obtener detalles de administradores.
 */
@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    // Inyección del servicio de administrador
    @Autowired
    private AdministratorService service;

    // Inyección del servicio de correo electrónico
    @Autowired
    private EmailService emailService;

    /**
     * Crea un nuevo administrador.
     * @param administrator DTO con los datos del administrador.
     * @param result resultados de validación.
     * @return ResponseEntity con el administrador creado o errores de validación.
     */
    // Endpoint POST para crear administrador
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdministratorDTO administrator, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(administrator));
    }

    /**
     * Cambia la contraseña de un administrador.
     * @param id ID del administrador.
     * @param dto DTO con la contraseña actual y nueva.
     * @return ResponseEntity con administrador actualizado o error de contraseña.
     */
    // Endpoint PUT para cambiar contraseña
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

    /**
     * Edita los datos de un administrador existente.
     * @param id ID del administrador.
     * @param user DTO con los datos a actualizar.
     * @param result resultados de validación.
     * @return ResponseEntity con administrador actualizado, 404 o errores de validación.
     */
    // Endpoint PUT para editar administrador
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

    /**
     * Obtiene detalle de un administrador específico.
     * @param accommodationId ID del administrador.
     * @return ResponseEntity con administrador o 404 si no existe.
     */
    // Endpoint GET para detalle de administrador
    @GetMapping("/getOne/{id}")
    public ResponseEntity<AdministratorDTO> detail(@PathVariable("id") int accommodationId) {
        AdministratorDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Registra un nuevo administrador y envía correo de bienvenida.
     * @param user DTO del administrador.
     * @param result resultados de validación.
     * @return ResponseEntity con administrador registrado o errores de validación.
     */
    // Endpoint POST para registrar administrador
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AdministratorDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        try {
            // Guardar administrador
            AdministratorDTO savedUser = service.save(user);

            // Enviar correo de bienvenida
            try {
                emailService.enviarCorreoBienvenida(savedUser.getEmail(), savedUser.getName());
            } catch (Exception e) {
                System.out.println("Error enviando correo: " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Duplicate entry: correo ya registrado"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Ocurrió un error inesperado"));
        }
    }

    /**
     * Login de administrador.
     * @param login DTO con email y contraseña.
     * @return ResponseEntity con administrador autenticado o 404 si no existe.
     */
    // Endpoint POST para login de administrador
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        AdministratorDTO admin = service.login(login);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    /**
     * Valida los campos de un BindingResult y retorna errores en formato JSON.
     * @param result BindingResult con errores de validación.
     * @return ResponseEntity con errores y status 400.
     */
    // Método privado para manejar errores de validación
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            // Genera mensaje de error para cada campo
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}