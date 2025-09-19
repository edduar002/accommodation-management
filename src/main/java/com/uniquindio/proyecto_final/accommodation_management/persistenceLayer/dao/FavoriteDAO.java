package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.FavoriteMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteDAO {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;

    public FavoriteDTO save(FavoriteDTO dto) {
        FavoriteEntity entity = favoriteMapper.toEntity(dto);
        FavoriteEntity savedEntity = favoriteRepository.save(entity);
        return favoriteMapper.toDTO(savedEntity);
    }
}