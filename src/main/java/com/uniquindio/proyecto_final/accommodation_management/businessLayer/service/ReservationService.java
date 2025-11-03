package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de reservas de alojamiento.
 * Define los métodos que implementarán las clases de servicio para crear,
 * consultar, aceptar, rechazar, cancelar y listar reservas.
 */
public interface ReservationService {

    /**
     * Guarda una nueva reserva en el sistema.
     * Método utilizado por el controlador para crear o registrar reservas.
     *
     * @param reservation DTO con los datos de la reserva a guardar.
     * @return Objeto ReservationDTO con la reserva registrada.
     */
    ReservationDTO save(ReservationDTO reservation);

    /**
     * Consulta los detalles de una reserva específica.
     * Método utilizado por el controlador en /viewReservationDetails/{idReservation}.
     *
     * @param idReservation ID de la reserva a consultar.
     * @return ReservationDTO con los detalles de la reserva.
     */
    ReservationDTO viewReservationDetails(int idReservation);

    /**
     * Lista el historial de reservas realizadas por un usuario.
     * Método utilizado por el controlador en /viewReservationHistory.
     *
     * @param idUser ID del usuario.
     * @return Lista de ReservationDTO con el historial de reservas del usuario.
     */
    List<ReservationDTO> viewReservationHistory(int idUser);

    /**
     * Cambia el estado de una reserva de forma genérica.
     * Método utilizado por el controlador en /changeStatus/{idReservation}.
     *
     * @param idReservation ID de la reserva a actualizar.
     * @return ResponseEntity con la reserva actualizada o null si no existe.
     */
    Optional<ReservationDTO> changeStatus(int idReservation, ReservationDTO user);

    /**
     * Lista todas las reservas de un anfitrión.
     * Método utilizado por el controlador en /viewReservations.
     *
     * @param idHost ID del anfitrión.
     * @return Lista de ReservationDTO o lista vacía si no hay reservas.
     */
    List<ReservationDTO> viewReservations(int idHost);

    /**
     * Cambia la calificion de una reserva de forma genérica.
     * Método utilizado por el controlador en /changeStatus/{idReservation}.
     *
     * @param idReservation ID de la reserva a actualizar.
     * @return ResponseEntity con la reserva actualizada o null si no existe.
     */
    Optional<ReservationDTO> saveRating(int idReservation, ReservationDTO user);
}