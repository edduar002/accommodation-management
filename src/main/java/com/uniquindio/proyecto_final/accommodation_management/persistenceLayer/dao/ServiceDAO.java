package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ServiceMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ServiceDAO {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceDTO save(ServiceDTO dto) {
        ServiceEntity entity = serviceMapper.toEntity(dto);
        ServiceEntity savedEntity = serviceRepository.save(entity);
        return serviceMapper.toDTO(savedEntity);
    }

    public List<ServiceDTO> servicesList() {
        List<ServiceEntity> entities = serviceRepository.findAll();
        return entities.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }
}