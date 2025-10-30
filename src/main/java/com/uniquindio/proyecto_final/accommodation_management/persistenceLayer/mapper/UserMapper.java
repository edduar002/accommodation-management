package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "rolesId", source = "rolesId")
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "departmentName", expression = "java(entity.getDepartment() != null ? entity.getDepartment().getName() : null)")
    UserDTO toDTO(UserEntity entity);

    List<UserDTO> toDTOList(List<UserEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "rolesId", source = "rolesId")
    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "citiesId", source = "citiesId")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(UserDTO dto);

    default DepartmentEntity map(Integer departmentId){
        if(departmentId == null){
            return null;
        }
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentId);
        return department;
    }

}