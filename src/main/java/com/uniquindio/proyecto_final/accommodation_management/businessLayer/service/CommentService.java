package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    CommentDTO save(CommentDTO comment);

    List<CommentDTO> commentsList(int idAccommodation);

    CommentDTO respondComments(int idComent, CommentDTO comment);
}
