package com.uniquindio.proyecto_final.accommodation_management.persistence.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.AccommodationService;
import org.springframework.data.repository.CrudRepository;

public interface AccommodationServiceRepository extends CrudRepository<AccommodationService, Integer> {
}
