package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ServiceEntity;

public interface ServiceService {

    ServiceDTO save(ServiceDTO service);
}
