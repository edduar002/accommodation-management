package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository repository;

    @Override
    @Transactional
    public CommentEntity save(CommentEntity dto) {
        return repository.save(dto);
    }

    @Override
    public ResponseEntity<List<CommentEntity>> commentsList(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<CommentEntity> respondComments(int idComent, CommentEntity comment) {
        return null;
    }
}
