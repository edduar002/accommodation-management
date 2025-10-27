package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AdministratorMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    public Optional<AdministratorDTO> findById(int id) {
        return administratorRepository.findById(id)
                .map(administratorMapper::toDTO);
    }

    public AdministratorDTO login(LoginDTO login) {
        AdministratorEntity entity = administratorRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        if (!entity.getPassword().equals(login.getPassword())) {
            return null;
        }
        return administratorMapper.toDTO(entity);
    }
}