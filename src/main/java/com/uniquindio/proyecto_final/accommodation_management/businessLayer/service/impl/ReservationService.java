package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ResponseEntity<ReservationDTO> save(ReservationDTO reservation);

    ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut);

    ResponseEntity<ReservationDTO> cancelReservations(int idReservation);

    ResponseEntity<List<AccommodationDTO>> viewReservationHistory(int idUser);

    ResponseEntity<AccommodationDTO> viewAccommodationDetails(int idAccommodation);
}
