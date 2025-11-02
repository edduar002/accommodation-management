package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio encargado de gestionar reservas de alojamiento.
 */
public interface ReservationService {

    ReservationDTO save(ReservationDTO reservation);

    ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut);

    ResponseEntity<ReservationDTO> cancelReservations(int idReservation);

    /**
     * Consulta el detalle de una reserva por su ID.
     */
    ReservationDTO viewReservationDetails(int idReservation);

    /**
     * Lista las reservas asociadas a un alojamiento.
     */
    List<ReservationDTO> viewAccommodationReservations(int idAccommodation);

    /**
     * Lista el historial de reservas de un usuario.
     */
    List<ReservationDTO> viewReservationHistory(int idUser);

    /**
     * Acepta una solicitud de reserva.
     */
    ResponseEntity<ReservationDTO> acceptReservationRequests(int idReservation);

    /**
     * Rechaza una solicitud de reserva.
     */
    ResponseEntity<ReservationDTO> rejectReservationRequests(int idReservation);
}