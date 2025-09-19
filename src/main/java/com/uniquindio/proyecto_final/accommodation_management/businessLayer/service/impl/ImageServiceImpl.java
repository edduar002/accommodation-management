package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.HostDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ImageDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageDAO dao;

    public ImageServiceImpl(ImageDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public ImageDTO save(ImageDTO dto) {
        return dao.save(dto);
    }

}
