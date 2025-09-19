package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ServiceMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}