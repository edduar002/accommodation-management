package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper para convertir entre AdministratorEntity y AdministratorDTO.
 *
 * Este mapper utiliza MapStruct para generar automáticamente la implementación
 * que realiza la conversión entre la entidad de persistencia y el DTO de negocio.
 */
@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    /**
     * Convierte una entidad AdministratorEntity a un DTO AdministratorDTO.
     * Mapea los campos básicos de la entidad y la referencia al rol.
     */
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "rolesId", source = "rolesId")
    AdministratorDTO toDTO(AdministratorEntity entity);

    /**
     * Convierte una lista de entidades AdministratorEntity a una lista de DTOs AdministratorDTO.
     * MapStruct aplica automáticamente la conversión para cada elemento de la lista.
     */
    List<AdministratorDTO> toDTOList(List<AdministratorEntity> entities);

    /**
     * Convierte un DTO AdministratorDTO a una entidad AdministratorEntity.
     *
     * Notas importantes:
     * - Los campos createdAt y updatedAt se ignoran al mapear DTO → Entity
     *   porque se gestionan automáticamente con @PrePersist y @PreUpdate en la entidad.
     * - Se mapea el id porque puede ser útil para actualizaciones.
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rolesId", source = "rolesId")
    AdministratorEntity toEntity(AdministratorDTO dto);
}