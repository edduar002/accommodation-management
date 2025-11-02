package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre ReservationEntity y ReservationDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    /**
     * Convierte una entidad ReservationEntity a un DTO ReservationDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el ID de la reserva
    @Mapping(target = "id", source = "id")
    // Mapea la fecha de check-in
    @Mapping(target = "checkIn", source = "checkIn")
    // Mapea la fecha de check-out
    @Mapping(target = "checkOut", source = "checkOut")
    // Mapea el estado de la reserva
    @Mapping(target = "state", source = "state")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    ReservationDTO toDTO(ReservationEntity entity);

    /**
     * Convierte una lista de entidades ReservationEntity a una lista de DTOs ReservationDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas de entidades a DTOs
    List<ReservationDTO> toDTOList(List<ReservationEntity> entities);

    /**
     * Convierte un DTO ReservationDTO a una entidad ReservationEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID de la reserva
    @Mapping(target = "id", source = "id")
    // Mapea la fecha de check-in
    @Mapping(target = "checkIn", source = "checkIn")
    // Mapea la fecha de check-out
    @Mapping(target = "checkOut", source = "checkOut")
    // Mapea el estado de la reserva
    @Mapping(target = "state", source = "state")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    ReservationEntity toEntity(ReservationDTO dto);

}