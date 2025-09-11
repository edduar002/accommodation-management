package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/create")
    public ResponseEntity<CommentEntity> create(@RequestBody CommentEntity comment){
        return service.save(comment);
    }

    @PostMapping("/respondComments")
    public ResponseEntity<CommentEntity> respondComments(@RequestParam int idComent, @RequestBody CommentEntity comment){
        return service.respondComments(idComent, comment);
    }

}
