package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteAccommodationMapper {

    @Mapping(target = "active", source = "active")
    @Mapping(target = "favoritesId", source = "favorite.id")
    @Mapping(target = "accommodationsId", source = "accommodation.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    FavoriteAccommodationDTO toDTO(FavoriteAccommodationEntity entity);

    List<FavoriteAccommodationDTO> toDTOList(List<FavoriteAccommodationEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "favorite.id", source = "favoritesId")
    @Mapping(target = "accommodation.id", source = "accommodationsId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FavoriteAccommodationEntity toEntity(FavoriteAccommodationDTO dto);
}