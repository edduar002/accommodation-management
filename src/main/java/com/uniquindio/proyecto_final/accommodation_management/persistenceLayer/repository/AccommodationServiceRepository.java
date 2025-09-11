package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationServiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationServiceRepository extends JpaRepository<AccommodationServiceDTO, Integer> {
}
