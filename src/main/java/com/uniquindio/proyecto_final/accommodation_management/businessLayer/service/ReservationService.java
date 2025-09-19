package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ReservationEntity save(ReservationEntity reservation);

    ResponseEntity<ReservationEntity> makeReservations(LocalDate checkIn, LocalDate checkOut);

    ResponseEntity<ReservationEntity> cancelReservations(int idReservation);

    ReservationEntity viewReservationDetails(int idAccommodation);

    List<ReservationEntity> viewAccommodationReservations(int idAccommodation);

    List<ReservationEntity> viewReservationHistory(int idUser);

    ResponseEntity<AccommodationEntity> acceptReservationRequests(int idAccommodation);

    ResponseEntity<AccommodationEntity> rejectReservationRequests(int idAccommodation);
}
