package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QualificationMapper {

    QualificationDTO toDTO(QualificationEntity entity);

    List<QualificationDTO> toDTOList(List<QualificationEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    QualificationEntity toEntity(QualificationDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(QualificationDTO dto, @MappingTarget QualificationEntity entity);
}