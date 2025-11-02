package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre DepartmentEntity y DepartmentDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    /**
     * Convierte una entidad DepartmentEntity a un DTO DepartmentDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el nombre de la entidad al DTO
    @Mapping(target = "name", source = "name")
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    DepartmentDTO toDTO(DepartmentEntity entity);

    /**
     * Convierte una lista de entidades DepartmentEntity a una lista de DTOs DepartmentDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas
    List<DepartmentDTO> toDTOList(List<DepartmentEntity> entities);

    /**
     * Convierte un DTO DepartmentDTO a una entidad DepartmentEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID del DTO a la entidad
    @Mapping(target = "id", source = "id")
    // Mapea el nombre
    @Mapping(target = "name", source = "name")
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    DepartmentEntity toEntity(DepartmentDTO dto);
}