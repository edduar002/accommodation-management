package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CityDTO toDTO(CityEntity entity);

    List<CityDTO> toDTOList(List<CityEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CityEntity toEntity(CityDTO dto);
}