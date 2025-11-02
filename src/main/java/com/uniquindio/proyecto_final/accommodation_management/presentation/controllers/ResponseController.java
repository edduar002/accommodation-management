package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de respuestas a comentarios.
 * Permite crear, registrar y listar respuestas asociadas a comentarios.
 */
@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    // Inyección del servicio de respuestas
    @Autowired
    private ResponseService service;

    /**
     * Crea una nueva respuesta a un comentario.
     * @param response DTO con los datos de la respuesta.
     * @param result resultados de validación.
     * @return ResponseEntity con la respuesta creada o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ResponseDTO response, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response));
    }

    /**
     * Registra una nueva respuesta a un comentario (alias de create con validación @Valid).
     * @param response DTO con los datos de la respuesta.
     * @param result resultados de validación.
     * @return ResponseEntity con la respuesta registrada o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ResponseDTO response, BindingResult result){
        return create(response, result);
    }

    /**
     * Lista todas las respuestas asociadas a un comentario específico.
     * @param commentId ID del comentario.
     * @return ResponseEntity con lista de respuestas o 204 si no hay contenido.
     */
    @GetMapping("/getByComment")
    public ResponseEntity<List<ResponseDTO>> getByComment(@RequestParam int commentId){
        List<ResponseDTO> propios = service.getByComment(commentId);
        if (propios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propios);
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