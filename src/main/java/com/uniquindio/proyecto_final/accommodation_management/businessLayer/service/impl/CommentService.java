package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<CommentEntity> save(CommentEntity comment);

    ResponseEntity<CommentEntity> respondComments(int idComent, CommentEntity comment);
}
