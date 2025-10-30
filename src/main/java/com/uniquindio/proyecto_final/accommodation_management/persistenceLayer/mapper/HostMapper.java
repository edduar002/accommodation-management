package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HostMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "rolesId", source = "rolesId")
    @Mapping(target = "personalDescription", source = "personalDescription")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "active", source = "active")
    HostDTO toDTO(HostEntity entity);

    List<HostDTO> toDTOList(List<HostEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "rolesId", source = "rolesId")
    @Mapping(target = "personalDescription", source = "personalDescription")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", source = "active")
    HostEntity toEntity(HostDTO dto);
}