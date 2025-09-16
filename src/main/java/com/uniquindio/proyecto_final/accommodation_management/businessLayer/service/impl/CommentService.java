package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    CommentEntity save(CommentEntity comment);

    ResponseEntity<List<CommentEntity>> commentsList(int idAccommodation);

    ResponseEntity<CommentEntity> respondComments(int idComent, CommentEntity comment);
}
