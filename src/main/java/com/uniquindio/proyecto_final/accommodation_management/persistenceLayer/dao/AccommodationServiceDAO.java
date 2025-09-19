package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AccommodationServiceMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccommodationServiceDAO {

    private final AccommodationServiceRepository accommodationServiceRepository;
    private final AccommodationServiceMapper accommodationServiceMapper;

    public AccommodationServiceDTO save(AccommodationServiceDTO dto) {
        AccommodationServiceEntity entity = accommodationServiceMapper.toEntity(dto);
        AccommodationServiceEntity savedEntity = accommodationServiceRepository.save(entity);
        return accommodationServiceMapper.toDTO(savedEntity);
    }
}