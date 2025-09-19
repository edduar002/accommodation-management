package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ImageMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageDAO {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageDTO save(ImageDTO dto) {
        ImageEntity entity = imageMapper.toEntity(dto);
        ImageEntity savedEntity = imageRepository.save(entity);
        return imageMapper.toDTO(savedEntity);
    }
}