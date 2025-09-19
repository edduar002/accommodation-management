package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.DepartmentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CommentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.DepartmentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO dao;

    public DepartmentServiceImpl(DepartmentDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public DepartmentDTO save(DepartmentDTO dto) {
        return dao.save(dto);
    }

}
