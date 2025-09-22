package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ImageEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ImageDTO toDTO(ImageEntity entity);

    List<ImageDTO> toDTOList(List<ImageEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ImageEntity toEntity(ImageDTO dto);
}