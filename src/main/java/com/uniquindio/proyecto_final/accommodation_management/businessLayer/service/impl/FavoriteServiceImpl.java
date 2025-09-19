package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteAccommodationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteDAO dao;

    public FavoriteServiceImpl(FavoriteDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public FavoriteDTO save(FavoriteDTO dto) {
        return dao.save(dto);
    }

}
