package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.DepartmentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DepartmentDAO {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentDTO save(DepartmentDTO dto) {
        DepartmentEntity entity = departmentMapper.toEntity(dto);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return departmentMapper.toDTO(savedEntity);
    }

    public List<DepartmentDTO> departmentsList() {
        List<DepartmentEntity> entities = departmentRepository.allDepartments();
        return entities.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> findById(int id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDTO);
    }
}