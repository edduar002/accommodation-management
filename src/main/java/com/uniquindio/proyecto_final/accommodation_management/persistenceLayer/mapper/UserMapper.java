package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre UserEntity y UserDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convierte una entidad UserEntity a un DTO UserDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el ID del usuario
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
    // Mapea el ID del departamento
    @Mapping(target = "departmentId", source = "department.id")
    // Mapea las ciudades por ID
    @Mapping(target = "citiesId", source = "citiesId")
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    // Mapea el nombre del departamento si existe, sino null
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    UserDTO toDTO(UserEntity entity);

    /**
     * Convierte una lista de entidades UserEntity a una lista de DTOs UserDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas de entidades a DTOs
    List<UserDTO> toDTOList(List<UserEntity> entities);

    /**
     * Convierte un DTO UserDTO a una entidad UserEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID del usuario
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
    // Mapea el departamento usando el método map
    @Mapping(target = "department", source = "departmentId")
    // Mapea las ciudades por ID
    @Mapping(target = "citiesId", source = "citiesId")
    // Mapea el estado activo
    @Mapping(target = "active", source = "active")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(UserDTO dto);

    /**
     * Convierte un ID de departamento a una entidad DepartmentEntity.
     * Retorna null si el ID es null.
     * @param departmentId ID del departamento.
     * @return entidad DepartmentEntity correspondiente.
     */
    // Mapea el ID a una entidad DepartmentEntity
    default DepartmentEntity map(Integer departmentId){
        if(departmentId == null){
            return null;
        }
        // Crea una nueva entidad y asigna el ID
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentId);
        return department;
    }
}