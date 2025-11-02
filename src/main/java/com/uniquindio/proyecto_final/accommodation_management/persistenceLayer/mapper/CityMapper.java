package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper para convertir entre CityEntity y CityDTO.
 *
 * Utiliza MapStruct para generar automáticamente la implementación
 * que transforma la entidad de persistencia en un DTO y viceversa.
 */
@Mapper(componentModel = "spring")
public interface CityMapper {

    /**
     * Convierte una entidad CityEntity a un DTO CityDTO.
     *
     * Mapea los campos básicos de la entidad y agrega el nombre del departamento
     * relacionado, evitando nulls si no existe la relación.
     */
    @Mapping(target = "name", source = "name")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    CityDTO toDTO(CityEntity entity);

    /**
     * Convierte una lista de entidades CityEntity a una lista de DTOs CityDTO.
     * MapStruct aplica automáticamente la conversión para cada elemento de la lista.
     */
    List<CityDTO> toDTOList(List<CityEntity> entities);

    /**
     * Convierte un DTO CityDTO a una entidad CityEntity.
     *
     * Notas importantes:
     * - Los campos createdAt y updatedAt se ignoran al mapear DTO → Entity
     *   porque se gestionan automáticamente con @PrePersist y @PreUpdate en la entidad.
     * - Se mapea el id porque puede ser útil para actualizaciones.
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CityEntity toEntity(CityDTO dto);
}