package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AdministratorMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdministratorDAO {

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    public AdministratorDTO save(AdministratorDTO dto) {
        AdministratorEntity entity = administratorMapper.toEntity(dto);
        AdministratorEntity savedEntity = administratorRepository.save(entity);
        return administratorMapper.toDTO(savedEntity);
    }
}