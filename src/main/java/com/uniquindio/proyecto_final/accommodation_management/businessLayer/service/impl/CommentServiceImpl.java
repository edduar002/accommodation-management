package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository repository;

    @Override
    public ResponseEntity<CommentEntity> save(CommentEntity comment) {
        return null;
    }

    @Override
    public ResponseEntity<CommentEntity> respondComments(int idComent, CommentEntity comment) {
        return null;
    }
}
