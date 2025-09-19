package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResponseCommentMapper {

    ResponseDTO toDTO(ResponseComentEntity entity);

    List<ResponseDTO> toDTOList(List<ResponseComentEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ResponseComentEntity toEntity(ResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ResponseDTO dto, @MappingTarget ResponseComentEntity entity);
}