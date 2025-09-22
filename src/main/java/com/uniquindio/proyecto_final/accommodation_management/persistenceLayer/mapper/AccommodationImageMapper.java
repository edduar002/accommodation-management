package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccommodationImageMapper {

    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "imagesId", source = "imagesId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    AccommodationImageDTO toDTO(AccommodationImageEntity entity);

    List<AccommodationImageDTO> toDTOList(List<AccommodationImageEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "imagesId", source = "imagesId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccommodationImageEntity toEntity(AccommodationImageDTO dto);
}