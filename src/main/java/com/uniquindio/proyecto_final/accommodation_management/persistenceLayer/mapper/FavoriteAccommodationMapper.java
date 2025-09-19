package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FavoriteAccommodationMapper {

    FavoriteAccommodationDTO toDTO(FavoriteAccommodationEntity entity);

    List<FavoriteAccommodationDTO> toDTOList(List<FavoriteAccommodationEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FavoriteAccommodationEntity toEntity(FavoriteAccommodationDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(FavoriteAccommodationDTO dto, @MappingTarget FavoriteAccommodationEntity entity);
}