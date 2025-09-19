package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HostMapper {

    HostDTO toDTO(HostEntity entity);

    List<HostDTO> toDTOList(List<HostEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    HostEntity toEntity(HostDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(HostDTO dto, @MappingTarget HostEntity entity);
}