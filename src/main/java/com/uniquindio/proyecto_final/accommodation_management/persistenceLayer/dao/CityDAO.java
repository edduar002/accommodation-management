package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.CityMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CityDAO {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityDTO save(CityDTO dto) {
        CityEntity entity = cityMapper.toEntity(dto);
        CityEntity savedEntity = cityRepository.save(entity);
        return cityMapper.toDTO(savedEntity);
    }
}