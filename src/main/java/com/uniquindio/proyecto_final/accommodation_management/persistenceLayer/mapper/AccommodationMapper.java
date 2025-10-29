package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccommodationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "detailedDescription", source = "detailedDescription")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "exactLocation", source = "exactLocation")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "maximumCapacity", source = "maximumCapacity")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "qualificationsId", source = "qualificationsId")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    AccommodationDTO toDTO(AccommodationEntity entity);

    List<AccommodationDTO> toDTOList(List<AccommodationEntity> entities);

    @Mapping(target = "id", source = "id")  // el id sí se mapea
    @Mapping(target = "detailedDescription", source = "detailedDescription")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "exactLocation", source = "exactLocation")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "maximumCapacity", source = "maximumCapacity")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "qualificationsId", source = "qualificationsId")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "createdAt", ignore = true)   // se controla con @PrePersist
    @Mapping(target = "updatedAt", ignore = true)   // se controla con @PreUpdate
    @Mapping(target = "active", source = "active")
    AccommodationEntity toEntity(AccommodationDTO dto);

}