package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.RoleService;
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
 * Controlador REST para la gestión de roles en el sistema.
 * Permite crear, registrar, listar, editar y eliminar roles.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    // Inyección del servicio de roles
    @Autowired
    private RoleService service;

    /**
     * Crea un nuevo rol en el sistema.
     * @param role DTO con los datos del rol.
     * @param result resultados de validación.
     * @return ResponseEntity con el rol creado o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleDTO role, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(role));
    }

    /**
     * Registra un rol en el sistema (alias de create con validación @Valid).
     * @param role DTO con los datos del rol.
     * @param result resultados de validación.
     * @return ResponseEntity con el rol registrado o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RoleDTO role, @Valid BindingResult result){
        return create(role, result);
    }

    /**
     * Lista todos los roles activos.
     * @return ResponseEntity con lista de roles o 204 si no hay contenido.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<RoleDTO>> rolesList(){
        List<RoleDTO> todos = service.rolesList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Obtiene el detalle de un rol por su ID.
     * @param accommodationId ID del rol.
     * @return ResponseEntity con el rol encontrado o 404 si no existe.
     */
    @GetMapping("/getOne/{id}")
    public ResponseEntity<RoleDTO> detail(@PathVariable("id") int accommodationId) {
        RoleDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Edita un rol existente.
     * @param id ID del rol.
     * @param user DTO con los datos actualizados.
     * @param result resultados de validación.
     * @return ResponseEntity con el rol editado o 404 si no existe.
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody RoleDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<RoleDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina un rol por su ID (soft delete o eliminación lógica).
     * @param id ID del rol.
     * @return ResponseEntity con el rol eliminado o 404 si no existe.
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<RoleDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
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