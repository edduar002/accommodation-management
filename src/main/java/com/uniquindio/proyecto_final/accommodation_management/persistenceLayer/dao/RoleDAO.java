package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.RoleMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleDAO {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDTO save(RoleDTO dto) {
        RoleEntity entity = roleMapper.toEntity(dto);
        RoleEntity savedEntity = roleRepository.save(entity);
        return roleMapper.toDTO(savedEntity);
    }

    public List<RoleDTO> rolesList() {
        List<RoleEntity> entities = roleRepository.findAll();
        return entities.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }
}