package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdministratorMapper {

    AdministratorDTO toDTO(AdministratorEntity entity);

    List<AdministratorDTO> toDTOList(List<AdministratorEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AdministratorEntity toEntity(AdministratorDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(AdministratorDTO dto, @MappingTarget AdministratorEntity entity);
}