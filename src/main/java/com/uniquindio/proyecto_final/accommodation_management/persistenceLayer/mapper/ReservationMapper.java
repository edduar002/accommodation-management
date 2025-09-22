package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "checkIn")
    @Mapping(target = "endDate", source = "checkOut")
    @Mapping(target = "status", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ReservationDTO toDTO(ReservationEntity entity);

    List<ReservationDTO> toDTOList(List<ReservationEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "checkIn", source = "startDate")
    @Mapping(target = "checkOut", source = "endDate")
    @Mapping(target = "state", source = "status")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ReservationEntity toEntity(ReservationDTO dto);

}