package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "active", source = "active")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    FavoriteDTO toDTO(FavoriteEntity entity);

    List<FavoriteDTO> toDTOList(List<FavoriteEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FavoriteEntity toEntity(FavoriteDTO dto);
}