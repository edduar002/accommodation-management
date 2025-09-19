package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.QualificationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.LegalDocumentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.QualificationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationDAO dao;

    public QualificationServiceImpl(QualificationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public QualificationDTO save(QualificationDTO dto) {
        return dao.save(dto);
    }

}
