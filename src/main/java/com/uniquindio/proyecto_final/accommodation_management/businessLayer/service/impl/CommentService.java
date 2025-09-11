package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<CommentDTO> save(CommentDTO comment);

    ResponseEntity<CommentDTO> respondComments(int idComent, CommentDTO comment);
}
