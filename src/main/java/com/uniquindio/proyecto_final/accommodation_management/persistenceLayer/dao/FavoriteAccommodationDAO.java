package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.FavoriteAccommodationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteAccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteAccommodationDAO {

    private final FavoriteAccommodationRepository favoriteAccommodationRepository;
    private final FavoriteAccommodationMapper favoriteAccommodationMapper;

    public FavoriteAccommodationDTO save(FavoriteAccommodationDTO dto) {
        FavoriteAccommodationEntity entity = favoriteAccommodationMapper.toEntity(dto);
        FavoriteAccommodationEntity savedEntity = favoriteAccommodationRepository.save(entity);
        return favoriteAccommodationMapper.toDTO(savedEntity);
    }
}