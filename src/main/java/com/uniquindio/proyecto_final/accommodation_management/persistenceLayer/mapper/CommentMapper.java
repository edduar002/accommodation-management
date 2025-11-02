package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper para convertir entre CommentEntity y CommentDTO.
 *
 * Utiliza MapStruct para generar automáticamente la implementación
 * que transforma la entidad de persistencia en un DTO y viceversa.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Convierte una entidad CommentEntity a un DTO CommentDTO.
     *
     * Mapea los campos básicos de la entidad y agrega el nombre del usuario
     * relacionado (userName), evitando nulls si no existe la relación.
     */
    @Mapping(target = "content", source = "content")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "usersId", source = "usersId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "userName", expression = "java(entity.getUser() != null ? entity.getUser().getName() : null)")
    CommentDTO toDTO(CommentEntity entity);

    /**
     * Convierte una lista de entidades CommentEntity a una lista de DTOs CommentDTO.
     * MapStruct aplica automáticamente la conversión para cada elemento de la lista.
     */
    List<CommentDTO> toDTOList(List<CommentEntity> entities);

    /**
     * Convierte un DTO CommentDTO a una entidad CommentEntity.
     *
     * Notas importantes:
     * - Los campos createdAt y updatedAt se ignoran al mapear DTO → Entity
     *   porque se gestionan automáticamente con @PrePersist y @PreUpdate en la entidad.
     * - Se mapea el id porque puede ser útil para actualizaciones.
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "usersId", source = "usersId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommentEntity toEntity(CommentDTO dto);
}