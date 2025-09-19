package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    ServiceDTO toDTO(ServiceEntity entity);

    List<ServiceDTO> toDTOList(List<ServiceEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ServiceEntity toEntity(ServiceDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ServiceDTO dto, @MappingTarget ServiceEntity entity);
}