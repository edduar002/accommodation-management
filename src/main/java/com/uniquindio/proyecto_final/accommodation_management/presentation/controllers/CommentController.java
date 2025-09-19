package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
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

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentEntity comment, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(comment));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CommentEntity comment, BindingResult result){
        return create(comment, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @PostMapping("/respondComments/{idComment}")
    public ResponseEntity<CommentEntity> respondComments(@PathVariable int idComment, @RequestBody CommentEntity comment, BindingResult result){
        return service.respondComments(idComment, comment);
    }

    @GetMapping("/commentsList/{idAccommodation}")
    public ResponseEntity<List<CommentEntity>> commentsList(@PathVariable("idAccommodation") int idAccommodation){
        List<CommentEntity> comentarios = service.commentsList(idAccommodation);
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

}
