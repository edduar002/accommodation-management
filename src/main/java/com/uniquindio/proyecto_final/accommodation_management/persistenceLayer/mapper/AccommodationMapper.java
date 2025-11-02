package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper para convertir entre AccommodationEntity y AccommodationDTO.
 *
 * Se utiliza MapStruct para generar automáticamente las implementaciones
 * de mapeo entre las entidades de persistencia y los DTOs del negocio.
 */
@Mapper(componentModel = "spring")
public interface AccommodationMapper {

    /**
     * Convierte una entidad AccommodationEntity a un DTO AccommodationDTO.
     * Incluye mapeo de campos simples y nombres de relaciones (departamento y ciudad).
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "detailedDescription", source = "detailedDescription")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "exactLocation", source = "exactLocation")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "maximumCapacity", source = "maximumCapacity")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "active", source = "active")
    // Se mapean los nombres de las entidades relacionadas si existen
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    @Mapping(target = "cityName", expression = "java(entity.getCity() != null ? entity.getCity().getName() : null)")
    AccommodationDTO toDTO(AccommodationEntity entity);

    /**
     * Convierte una lista de entidades AccommodationEntity a una lista de DTOs AccommodationDTO.
     * MapStruct maneja automáticamente el mapeo de cada elemento.
     */
    List<AccommodationDTO> toDTOList(List<AccommodationEntity> entities);

    /**
     * Convierte un DTO AccommodationDTO a una entidad AccommodationEntity.
     *
     * Notas importantes:
     * - Los campos createdAt y updatedAt se ignoran porque se gestionan automáticamente
     *   con las anotaciones @PrePersist y @PreUpdate en la entidad.
     * - El campo id sí se mapea porque puede ser útil para actualizaciones.
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "detailedDescription", source = "detailedDescription")
    @Mapping(target = "direction", source = "direction")
    @Mapping(target = "exactLocation", source = "exactLocation")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "maximumCapacity", source = "maximumCapacity")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "departmentsId", source = "departmentsId")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", source = "active")
    AccommodationEntity toEntity(AccommodationDTO dto);
}