package com.uniquindio.proyecto_final.accommodation_management.persistence.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
