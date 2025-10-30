package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseCommentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "commentsId", source = "commentsId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "hostName", expression = "java(entity.getHost() != null ? entity.getHost().getName() : null)")
    ResponseDTO toDTO(ResponseCommentEntity entity);

    List<ResponseDTO> toDTOList(List<ResponseCommentEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "commentsId", source = "commentsId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ResponseCommentEntity toEntity(ResponseDTO dto);
}