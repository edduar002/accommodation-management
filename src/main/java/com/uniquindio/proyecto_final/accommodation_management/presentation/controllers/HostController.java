package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
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
 * Controlador REST para la entidad Host.
 * Proporciona endpoints para crear, registrar, editar, eliminar, listar y autenticar hosts.
 */
@RestController
@RequestMapping("/api/hosts")
public class HostController {

    // Inyección del servicio de hosts
    @Autowired
    private HostService service;

    // Inyección del servicio de correo
    @Autowired
    private EmailService emailService;

    /**
     * Crea un nuevo host.
     * @param host DTO con los datos del host.
     * @param result resultados de validación.
     * @return ResponseEntity con host creado o errores de validación.
     */
    // Endpoint POST para crear host
    @PostMapping
    public ResponseEntity<?> create(@RequestBody HostDTO host, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(host));
    }

    /**
     * Obtiene detalle de un host específico.
     * @param accommodationId ID del host.
     * @return ResponseEntity con host o 404 si no existe.
     */
    // Endpoint GET para detalle de host
    @GetMapping("/getOne/{id}")
    public ResponseEntity<HostDTO> detail(@PathVariable("id") int accommodationId) {
        HostDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Registra un host y envía correo de bienvenida.
     * @param user DTO con los datos del host.
     * @param result resultados de validación.
     * @return ResponseEntity con host registrado o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody HostDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        try {
            // Guardar host
            HostDTO savedUser = service.save(user);

            // Enviar correo de bienvenida
            try {
                emailService.enviarCorreoBienvenida(savedUser.getEmail(), savedUser.getName());
            } catch (Exception e) {
                // Si el correo falla, solo lo logeamos, no bloqueamos el registro
                System.out.println("Error enviando correo: " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // Captura duplicados de llave única (correo ya registrado)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Duplicate entry: correo ya registrado"));
        } catch (Exception e) {
            // Error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Ocurrió un error inesperado"));
        }
    }

    /**
     * Login de host.
     * @param login DTO con email y contraseña.
     * @return ResponseEntity con host autenticado o 404 si no existe.
     */
    // Endpoint POST para login de host
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        HostDTO host = service.login(login);
        if (host == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(host);
    }

    /**
     * Marca un host como eliminado.
     * @param id ID del host.
     * @return ResponseEntity con host eliminado o 404 si no existe.
     */
    // Endpoint PUT para eliminar host
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<HostDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Lista todos los hosts activos.
     * @return ResponseEntity con lista de hosts o 204 si no hay contenido.
     */
    // Endpoint GET para listar todos los hosts
    @GetMapping("/getAll")
    public ResponseEntity<List<HostDTO>> hostsList(){
        List<HostDTO> todos = service.hostsList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Edita un host existente.
     * @param id ID del host.
     * @param host DTO con datos a actualizar.
     * @param result resultados de validación.
     * @return ResponseEntity con host actualizado, 404 o errores de validación.
     */
    // Endpoint PUT para editar host
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

    /**
     * Cambia la contraseña de un host.
     * @param id ID del host.
     * @param dto DTO con contraseña actual y nueva.
     * @return ResponseEntity con host actualizado o error de contraseña incorrecta.
     */
    // Endpoint PUT para cambiar contraseña
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