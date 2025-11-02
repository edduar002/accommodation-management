package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper que convierte entre ResponseCommentEntity y ResponseDTO.
 * Utiliza MapStruct para generar el código de mapeo automáticamente.
 */
@Mapper(componentModel = "spring")
public interface ResponseCommentMapper {

    /**
     * Convierte una entidad ResponseCommentEntity a un DTO ResponseDTO.
     * @param entity entidad a convertir.
     * @return DTO correspondiente.
     */
    // Mapea el ID de la respuesta
    @Mapping(target = "id", source = "id")
    // Mapea el contenido de la respuesta
    @Mapping(target = "content", source = "content")
    // Mapea el ID del host que respondió
    @Mapping(target = "hostsId", source = "hostsId")
    // Mapea el ID del comentario al que se responde
    @Mapping(target = "commentsId", source = "commentsId")
    // Mapea la fecha de la respuesta
    @Mapping(target = "date", source = "date")
    // Mapea la fecha de creación
    @Mapping(target = "createdAt", source = "createdAt")
    // Mapea la fecha de actualización
    @Mapping(target = "updatedAt", source = "updatedAt")
    // Mapea el nombre del host si existe, sino null
    @Mapping(target = "hostName", expression = "java(entity.getHost() != null ? entity.getHost().getName() : null)")
    ResponseDTO toDTO(ResponseCommentEntity entity);

    /**
     * Convierte una lista de entidades ResponseCommentEntity a una lista de DTOs ResponseDTO.
     * @param entities lista de entidades.
     * @return lista de DTOs correspondientes.
     */
    // Conversión de listas de entidades a DTOs
    List<ResponseDTO> toDTOList(List<ResponseCommentEntity> entities);

    /**
     * Convierte un DTO ResponseDTO a una entidad ResponseCommentEntity.
     * Ignora createdAt y updatedAt porque se gestionan automáticamente.
     * @param dto DTO a convertir.
     * @return entidad correspondiente.
     */
    // Mapea el ID de la respuesta
    @Mapping(target = "id", source = "id")
    // Mapea el contenido de la respuesta
    @Mapping(target = "content", source = "content")
    // Mapea el ID del host que respondió
    @Mapping(target = "hostsId", source = "hostsId")
    // Mapea el ID del comentario al que se responde
    @Mapping(target = "commentsId", source = "commentsId")
    // Mapea la fecha de la respuesta
    @Mapping(target = "date", source = "date")
    // Ignora la fecha de creación
    @Mapping(target = "createdAt", ignore = true)
    // Ignora la fecha de actualización
    @Mapping(target = "updatedAt", ignore = true)
    ResponseCommentEntity toEntity(ResponseDTO dto);
}