package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QualificationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "numberStars", source = "numberStars")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    QualificationDTO toDTO(QualificationEntity entity);

    List<QualificationDTO> toDTOList(List<QualificationEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "numberStars", source = "numberStars")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    QualificationEntity toEntity(QualificationDTO dto);
}