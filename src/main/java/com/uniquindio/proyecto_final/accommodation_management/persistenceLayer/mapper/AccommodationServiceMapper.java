package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccommodationServiceMapper {

    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "servicesId", source = "servicesId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    AccommodationServiceDTO toDTO(AccommodationServiceEntity entity);

    List<AccommodationServiceDTO> toDTOList(List<AccommodationServiceEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "servicesId", source = "servicesId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccommodationServiceEntity toEntity(AccommodationServiceDTO dto);
}