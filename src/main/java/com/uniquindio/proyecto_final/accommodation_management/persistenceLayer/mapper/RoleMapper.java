package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre RoleEntity y RoleDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    /**
     * Convierte una entidad RoleEntity a un DTO RoleDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el ID del rol
    @Mapping(target = "id", source = "id")
    // Mapea el nombre del rol
    @Mapping(target = "name", source = "name")
    // Mapea el estado activo del rol
    @Mapping(target = "active", source = "active")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    RoleDTO toDTO(RoleEntity entity);

    /**
     * Convierte una lista de entidades RoleEntity a una lista de DTOs RoleDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas de entidades a DTOs
    List<RoleDTO> toDTOList(List<RoleEntity> entities);

    /**
     * Convierte un DTO RoleDTO a una entidad RoleEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID del rol
    @Mapping(target = "id", source = "id")
    // Mapea el nombre del rol
    @Mapping(target = "name", source = "name")
    // Mapea el estado activo del rol
    @Mapping(target = "active", source = "active")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    RoleEntity toEntity(RoleDTO dto);
}