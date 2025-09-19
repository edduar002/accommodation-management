package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CityService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AdministratorDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CityDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

    private final CityDAO dao;

    public CityServiceImpl(CityDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public CityDTO save(CityDTO dto) {
        return dao.save(dto);
    }
}
