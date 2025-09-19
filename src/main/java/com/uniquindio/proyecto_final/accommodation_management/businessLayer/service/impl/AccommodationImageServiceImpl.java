package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationImageDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationImageServiceImpl implements AccommodationImageService {

    private final AccommodationImageDAO dao;

    public AccommodationImageServiceImpl(AccommodationImageDAO dao) {
        this.dao = dao;
    }

    @Override
    public AccommodationImageDTO save(AccommodationImageDTO dto) {
        return dao.save(dto);
    }

}