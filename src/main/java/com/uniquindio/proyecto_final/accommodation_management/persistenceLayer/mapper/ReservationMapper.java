package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    ReservationDTO toDTO(ReservationEntity entity);

    List<ReservationDTO> toDTOList(List<ReservationEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ReservationEntity toEntity(ReservationDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ReservationDTO dto, @MappingTarget ReservationEntity entity);
}