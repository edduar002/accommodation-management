package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre HostEntity y HostDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface HostMapper {

    /**
     * Convierte una entidad HostEntity a un DTO HostDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el nombre
    @Mapping(target = "name", source = "name")
    // Mapea el apellido
    @Mapping(target = "surname", source = "surname")
    // Mapea el correo electrónico
    @Mapping(target = "email", source = "email")
    // Mapea la contraseña
    @Mapping(target = "password", source = "password")
    // Mapea el teléfono
    @Mapping(target = "phone", source = "phone")
    // Mapea la fecha de cumpleaños
    @Mapping(target = "birthday", source = "birthday")
    // Mapea la URL de la imagen
    @Mapping(target = "imgUrl", source = "imgUrl")
    // Mapea los roles por ID
    @Mapping(target = "rolesId", source = "rolesId")
    // Mapea la descripción personal
    @Mapping(target = "personalDescription", source = "personalDescription")
    // Mapea los departamentos por ID
    @Mapping(target = "departmentsId", source = "departmentsId")
    // Mapea las ciudades por ID
    @Mapping(target = "citiesId", source = "citiesId")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    // Mapea el nombre del departamento si existe, sino null
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    HostDTO toDTO(HostEntity entity);

    /**
     * Convierte una lista de entidades HostEntity a una lista de DTOs HostDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas de entidades a DTOs
    List<HostDTO> toDTOList(List<HostEntity> entities);

    /**
     * Convierte un DTO HostDTO a una entidad HostEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID del DTO a la entidad
    @Mapping(target = "id", source = "id")
    // Mapea el nombre
    @Mapping(target = "name", source = "name")
    // Mapea el apellido
    @Mapping(target = "surname", source = "surname")
    // Mapea el correo electrónico
    @Mapping(target = "email", source = "email")
    // Mapea la contraseña
    @Mapping(target = "password", source = "password")
    // Mapea el teléfono
    @Mapping(target = "phone", source = "phone")
    // Mapea la fecha de cumpleaños
    @Mapping(target = "birthday", source = "birthday")
    // Mapea la URL de la imagen
    @Mapping(target = "imgUrl", source = "imgUrl")
    // Mapea los roles por ID
    @Mapping(target = "rolesId", source = "rolesId")
    // Mapea la descripción personal
    @Mapping(target = "personalDescription", source = "personalDescription")
    // Mapea los departamentos por ID
    @Mapping(target = "departmentsId", source = "departmentsId")
    // Mapea las ciudades por ID
    @Mapping(target = "citiesId", source = "citiesId")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    HostEntity toEntity(HostDTO dto);
}