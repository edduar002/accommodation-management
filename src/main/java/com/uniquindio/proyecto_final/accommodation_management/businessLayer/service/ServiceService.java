package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    ServiceDTO save(ServiceDTO service);

    List<ServiceDTO> servicesList();

    ServiceDTO detail(int accommodation);

    Optional<ServiceDTO> edit(int idAccommodation, ServiceDTO user);

    Optional<ServiceDTO> delete(int idAccommodation);
}
