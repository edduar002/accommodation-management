package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.City;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment){
        return service.save(comment);
    }

    @PostMapping
    public ResponseEntity<Comment> respondComments(@RequestParam int idComent, @RequestBody Comment comment){
        return service.respondComments(idComent, comment);
    }

}
