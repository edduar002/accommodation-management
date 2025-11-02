package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CommentService;
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
 * Controlador REST para la entidad Comment.
 * Proporciona endpoints para crear, registrar, responder y listar comentarios.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    // Inyección del servicio de comentarios
    @Autowired
    private CommentService service;

    /**
     * Crea un nuevo comentario.
     * @param comment DTO con los datos del comentario.
     * @param result resultados de validación.
     * @return ResponseEntity con comentario creado o errores de validación.
     */
    // Endpoint POST para crear comentario
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDTO comment, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(comment));
    }

    /**
     * Registra un comentario (alias de create con validación @Valid).
     * @param comment DTO del comentario.
     * @param result resultados de validación.
     * @return ResponseEntity con comentario registrado o errores.
     */
    // Endpoint POST para registrar comentario
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CommentDTO comment, BindingResult result){
        return create(comment, result);
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

    /**
     * Responde a un comentario existente.
     * @param idComment ID del comentario al que se responde.
     * @param comment DTO con la respuesta.
     * @param result resultados de validación.
     * @return ResponseEntity con el comentario respondido.
     */
    // Endpoint POST para responder comentarios
    @PostMapping("/respondComments/{idComment}")
    public ResponseEntity<CommentDTO> respondComments(@PathVariable int idComment, @RequestBody CommentDTO comment, BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.respondComments(idComment, comment));
    }

    /**
     * Lista los comentarios de un alojamiento específico.
     * @param idAccommodation ID del alojamiento.
     * @return ResponseEntity con lista de comentarios o 204 si no hay contenido.
     */
    // Endpoint GET para listar comentarios de un alojamiento
    @GetMapping("/commentsList/{idAccommodation}")
    public ResponseEntity<List<CommentDTO>> commentsList(@PathVariable("idAccommodation") int idAccommodation){
        List<CommentDTO> comentarios = service.commentsList(idAccommodation);
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

}