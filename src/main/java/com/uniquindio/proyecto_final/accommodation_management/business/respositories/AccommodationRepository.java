package com.uniquindio.proyecto_final.accommodation_management.business.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import org.springframework.data.repository.CrudRepository;

public interface AccommodationRepository extends CrudRepository<Accommodation, Integer> {
}
