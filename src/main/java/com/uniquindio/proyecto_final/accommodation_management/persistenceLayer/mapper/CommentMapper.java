package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "content", source = "content")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "usersId", source = "usersId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CommentDTO toDTO(CommentEntity entity);

    List<CommentDTO> toDTOList(List<CommentEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "accommodationsId", source = "accommodationsId")
    @Mapping(target = "usersId", source = "usersId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommentEntity toEntity(CommentDTO dto);
}