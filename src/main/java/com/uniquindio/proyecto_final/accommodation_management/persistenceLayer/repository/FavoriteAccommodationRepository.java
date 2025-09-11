package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteAccommodationRepository extends JpaRepository<FavoriteAccommodationDTO, Integer> {
}
