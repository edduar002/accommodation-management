package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccommodationImageMapper {

    AccommodationImageDTO toDTO(AccommodationImageEntity entity);

    List<AccommodationImageDTO> toDTOList(List<AccommodationImageEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccommodationImageEntity toEntity(AccommodationImageDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(AccommodationImageDTO dto, @MappingTarget AccommodationImageEntity entity);
}