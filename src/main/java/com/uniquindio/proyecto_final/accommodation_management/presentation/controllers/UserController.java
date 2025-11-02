package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para la gestión de usuarios en el sistema.
 * Permite crear, registrar, listar, editar, eliminar usuarios y manejar autenticación.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private EmailService emailService;

    /**
     * Crea un nuevo usuario.
     * @param user DTO con los datos del usuario.
     * @param result resultados de validación.
     * @return ResponseEntity con el usuario creado o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    /**
     * Registra un usuario con envío de correo de bienvenida.
     * @param user DTO con los datos del usuario.
     * @param result resultados de validación.
     * @return ResponseEntity con el usuario registrado o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        try {
            // Guardar usuario
            UserDTO savedUser = service.save(user);

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
     * Obtiene el detalle de un usuario por su ID.
     * @param accommodationId ID del usuario.
     * @return ResponseEntity con el usuario o 404 si no existe.
     */
    @GetMapping("/getOne/{id}")
    public ResponseEntity<UserDTO> detail(@PathVariable("id") int accommodationId) {
        UserDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Inicia sesión con correo y contraseña.
     * @param login DTO con email y password.
     * @return ResponseEntity con el usuario autenticado o 404 si no existe.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        UserDTO user = service.login(login);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Edita un usuario existente.
     * @param id ID del usuario.
     * @param user DTO con los datos actualizados.
     * @param result resultados de validación.
     * @return ResponseEntity con el usuario editado o 404 si no existe.
     */
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

    /**
     * Lista todos los usuarios activos.
     * @return ResponseEntity con lista de usuarios o 204 si no hay contenido.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> usersList(){
        List<UserDTO> todos = service.usersList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Elimina un usuario (soft delete).
     * @param id ID del usuario.
     * @return ResponseEntity con el usuario eliminado o 404 si no existe.
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<UserDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Cambia la contraseña de un usuario.
     * @param id ID del usuario.
     * @param dto DTO con contraseña actual y nueva.
     * @return ResponseEntity con el usuario actualizado o error si la contraseña es incorrecta.
     */
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

    /**
     * Valida los campos de un BindingResult y retorna errores en formato JSON.
     * @param result BindingResult con errores de validación.
     * @return ResponseEntity con errores y status 400.
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}