package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de reservas.
 * Proporciona endpoints para crear, registrar, cancelar, aceptar, rechazar y listar reservas.
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    // Inyección del servicio de reservas
    @Autowired
    private ReservationService service;

    /**
     * Crea una nueva reserva.
     * @param reservation DTO con los datos de la reserva.
     * @param result resultados de validación.
     * @return ResponseEntity con reserva creada o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationDTO reservation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservation));
    }

    /**
     * Registra una nueva reserva (alias de create con validación @Valid).
     * @param reservation DTO con los datos de la reserva.
     * @param result resultados de validación.
     * @return ResponseEntity con reserva registrada o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ReservationDTO reservation, BindingResult result){
        return create(reservation, result);
    }

    /**
     * Cancela una reserva existente.
     * @param idReservation ID de la reserva a cancelar.
     * @return ResponseEntity con la reserva cancelada o 404 si no existe.
     */
    @PutMapping("/cancelReservations/{idReservation}")
    public ResponseEntity<ReservationDTO> cancelReservations(@PathVariable int idReservation) {
        ReservationDTO updatedReservation = service.cancelReservations(idReservation).getBody();
        if (updatedReservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Muestra detalles de una reserva para un alojamiento específico.
     * @param accommodationId ID del alojamiento.
     * @return ResponseEntity con detalles de la reserva o 404 si no existe.
     */
    @GetMapping("/viewReservationDetails/{idAccommodation}")
    public ResponseEntity<ReservationDTO> viewReservationDetails(@PathVariable("idAccommodation") int accommodationId) {
        ReservationDTO detalle = service.viewReservationDetails(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Valida los campos de un BindingResult y retorna errores en formato JSON.
     * @param result BindingResult con errores de validación.
     * @return ResponseEntity con errores y status 400.
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Lista todas las reservas de un alojamiento específico.
     * @param idAccommodation ID del alojamiento.
     * @return ResponseEntity con lista de reservas o 204 si no hay contenido.
     */
    @GetMapping("/viewAccommodationReservations/{idAccommodation}")
    public ResponseEntity<List<ReservationDTO>> viewAccommodationReservations(@PathVariable("idAccommodation") int idAccommodation){
        List<ReservationDTO> reservas = service.viewAccommodationReservations(idAccommodation);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /**
     * Muestra el historial de reservas de un usuario.
     * @param idUser ID del usuario.
     * @return ResponseEntity con lista de reservas o 204 si no hay contenido.
     */
    @GetMapping("/viewReservationHistory")
    public ResponseEntity<List<ReservationDTO>> viewReservationHistory(@RequestParam int idUser){
        List<ReservationDTO> reservas = service.viewReservationHistory(idUser);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /**
     * Acepta una solicitud de reserva.
     * @param idReservation ID de la reserva.
     * @return ResponseEntity con la reserva aceptada o 404 si no existe.
     */
    @PutMapping("/acceptReservationRequests/{idReservation}")
    public ResponseEntity<ReservationDTO> acceptReservationRequests(@PathVariable int idReservation){
        ReservationDTO updatedReservation = service.acceptReservationRequests(idReservation).getBody();
        if (updatedReservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Rechaza una solicitud de reserva.
     * @param idReservation ID de la reserva.
     * @return ResponseEntity con la reserva rechazada o 404 si no existe.
     */
    @PutMapping("/rejectReservationRequests/{idReservation}")
    public ResponseEntity<ReservationDTO> rejectReservationRequests(@PathVariable int idReservation){
        ReservationDTO updatedReservation = service.rejectReservationRequests(idReservation).getBody();
        if (updatedReservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReservation);
    }
}