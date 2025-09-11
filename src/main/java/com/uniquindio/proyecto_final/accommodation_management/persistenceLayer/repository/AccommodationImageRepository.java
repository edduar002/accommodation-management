package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationImageRepository extends JpaRepository<AccommodationImageDTO, Integer> {
}
