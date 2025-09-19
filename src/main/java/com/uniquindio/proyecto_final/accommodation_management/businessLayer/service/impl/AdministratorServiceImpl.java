package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AdministratorDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorDAO dao;

    public AdministratorServiceImpl(AdministratorDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public AdministratorDTO save(AdministratorDTO dto) {
        return dao.save(dto);
    }

}
