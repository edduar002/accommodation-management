package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

    ImageDTO toDTO(ImageEntity entity);

    List<ImageDTO> toDTOList(List<ImageEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ImageEntity toEntity(ImageDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ImageDTO dto, @MappingTarget ImageEntity entity);
}