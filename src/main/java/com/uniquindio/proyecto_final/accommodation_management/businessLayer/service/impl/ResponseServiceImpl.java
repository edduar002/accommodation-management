package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ResponseService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ResponseCommentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseCommentDAO dao;

    public ResponseServiceImpl(ResponseCommentDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public ResponseDTO save(ResponseDTO dto) {
        return dao.save(dto);
    }
}
