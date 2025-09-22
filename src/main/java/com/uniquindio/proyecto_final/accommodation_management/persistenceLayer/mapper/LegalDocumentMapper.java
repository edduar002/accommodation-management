package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.LegalDocumentEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LegalDocumentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "urlDocument", source = "urlDocument")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    LegalDocumentDTO toDTO(LegalDocumentEntity entity);

    List<LegalDocumentDTO> toDTOList(List<LegalDocumentEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "urlDocument", source = "urlDocument")
    @Mapping(target = "hostsId", source = "hostsId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LegalDocumentEntity toEntity(LegalDocumentDTO dto);

}