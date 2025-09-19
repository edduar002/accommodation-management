package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentDTO toDTO(CommentEntity entity);

    List<CommentDTO> toDTOList(List<CommentEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommentEntity toEntity(CommentDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CommentDTO dto, @MappingTarget CommentEntity entity);
}