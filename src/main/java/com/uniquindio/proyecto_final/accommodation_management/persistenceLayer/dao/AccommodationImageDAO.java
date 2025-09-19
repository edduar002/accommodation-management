package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AccommodationImageMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccommodationImageDAO {

    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationImageMapper accommodationImageMapper;

    public AccommodationImageDTO save(AccommodationImageDTO dto) {
        AccommodationImageEntity entity = accommodationImageMapper.toEntity(dto);
        AccommodationImageEntity savedEntity = accommodationImageRepository.save(entity);
        return accommodationImageMapper.toDTO(savedEntity);
    }
}