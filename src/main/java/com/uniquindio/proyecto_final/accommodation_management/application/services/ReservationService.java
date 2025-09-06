package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ResponseEntity<Reservation> save(Reservation reservation);

    ResponseEntity<Reservation> makeReservations(LocalDate checkIn, LocalDate checkOut);

    ResponseEntity<Reservation> cancelReservations(int idReservation);

    ResponseEntity<List<Accommodation>> viewReservationHistory(int idUser);

    ResponseEntity<Accommodation> viewAccommodationDetails(int idAccommodation);
}
