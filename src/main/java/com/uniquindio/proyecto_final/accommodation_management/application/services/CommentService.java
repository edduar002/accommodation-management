package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<Comment> save(Comment comment);

    ResponseEntity<Comment> respondComments(int idComent, Comment comment);
}
