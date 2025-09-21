package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CityDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CommentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDAO dao;

    public CommentServiceImpl(CommentDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public CommentDTO save(CommentDTO dto) {
        return dao.save(dto);
    }

    @Override
    public List<CommentDTO> commentsList(int idAccommodation) {
        return List.of();
    }

    @Override
    public CommentDTO respondComments(int idComent, CommentDTO comment) {
        return null;
    }

}
