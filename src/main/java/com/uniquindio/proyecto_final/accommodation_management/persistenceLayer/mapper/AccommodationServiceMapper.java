package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccommodationServiceMapper {

    AccommodationServiceDTO toDTO(AccommodationServiceEntity entity);

    List<AccommodationServiceDTO> toDTOList(List<AccommodationServiceEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccommodationServiceEntity toEntity(AccommodationServiceDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(AccommodationServiceDTO dto, @MappingTarget AccommodationServiceEntity entity);
}
