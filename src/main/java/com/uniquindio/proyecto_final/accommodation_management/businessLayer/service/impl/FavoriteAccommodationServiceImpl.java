package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteAccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.DepartmentDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteAccommodationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteAccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteAccommodationServiceImpl implements FavoriteAccommodationService {

    private final FavoriteAccommodationDAO dao;

    public FavoriteAccommodationServiceImpl(FavoriteAccommodationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public FavoriteAccommodationDTO save(FavoriteAccommodationDTO dto) {
        return dao.save(dto);
    }

}
