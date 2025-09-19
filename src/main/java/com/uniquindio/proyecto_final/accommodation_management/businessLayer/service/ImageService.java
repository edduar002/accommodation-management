package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;

public interface ImageService {

    ImageDTO save(ImageDTO image);
}
