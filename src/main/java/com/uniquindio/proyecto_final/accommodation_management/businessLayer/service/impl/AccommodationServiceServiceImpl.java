package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationServiceDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationServiceServiceImpl implements AccommodationServiceService {

    private final AccommodationServiceDAO dao;

    public AccommodationServiceServiceImpl(AccommodationServiceDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public AccommodationServiceDTO save(AccommodationServiceDTO dto) {
        return dao.save(dto);
    }

}
