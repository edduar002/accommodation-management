package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ReservationDTO save(ReservationDTO reservation);

    ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut);

    ResponseEntity<ReservationDTO> cancelReservations(int idReservation);

    ReservationDTO viewReservationDetails(int idAccommodation);

    List<ReservationDTO> viewAccommodationReservations(int idAccommodation);

    List<ReservationDTO> viewReservationHistory(int idUser);

    ResponseEntity<AccommodationDTO> acceptReservationRequests(int idAccommodation);

    ResponseEntity<AccommodationDTO> rejectReservationRequests(int idAccommodation);
}
