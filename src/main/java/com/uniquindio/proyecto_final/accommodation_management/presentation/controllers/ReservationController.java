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

    // Inyección del servicio de reservas que contiene la lógica de negocio
    @Autowired
    private ReservationService service;

    /**
     * Crea una nueva reserva.
     * Se encarga de validar los datos y llamar al servicio para guardar la reserva.
     *
     * @param reservation DTO con los datos de la reserva.
     * @param result Objeto que contiene los errores de validación.
     * @return ResponseEntity con la reserva creada o con errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationDTO reservation, BindingResult result){
        if(result.hasFieldErrors()){
            // Si hay errores de validación, devuelve un JSON con los errores
            return validation(result);
        }
        // Llama al servicio para guardar la reserva y retorna el resultado con status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservation));
    }

    /**
     * Registra una nueva reserva (alias de create con validación @Valid).
     * Se utiliza para endpoints que requieren validación automática.
     *
     * @param reservation DTO con los datos de la reserva.
     * @param result Objeto con errores de validación.
     * @return ResponseEntity con la reserva registrada o errores de validación.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ReservationDTO reservation, BindingResult result){
        return create(reservation, result);
    }

    /**
     * Cambia el estado de una reserva existente.
     * Por ejemplo, puede pasar de "Pendiente" a "Aprobado" o "Cancelada".
     *
     * @param idReservation ID de la reserva a actualizar.
     * @return ResponseEntity con la reserva actualizada o 404 si no existe.
     */
    @PutMapping("/changeStatus/{idReservation}")
    public ResponseEntity<ReservationDTO> changeStatus(@PathVariable int idReservation) {
        ReservationDTO updatedReservation = service.changeStatus(idReservation).getBody();
        if (updatedReservation == null) {
            // Si no se encuentra la reserva, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Obtiene los detalles de una reserva específica.
     *
     * @param accommodationId ID de la reserva a consultar.
     * @return ResponseEntity con los detalles de la reserva o 404 si no existe.
     */
    @GetMapping("/viewReservationDetails/{idReservation}")
    public ResponseEntity<ReservationDTO> viewReservationDetails(@PathVariable("idReservation") int accommodationId) {
        ReservationDTO detalle = service.viewReservationDetails(accommodationId);
        if (detalle == null) {
            // Si no se encuentra la reserva, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Valida los campos de un BindingResult y retorna los errores en formato JSON.
     *
     * @param result Objeto con errores de validación.
     * @return ResponseEntity con errores y status 400 BAD REQUEST.
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacío: " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Obtiene el historial de reservas de un anfitrión.
     *
     * @param idHost ID del anfitrión.
     * @return ResponseEntity con lista de reservas o 204 No Content si no hay reservas.
     */
    @GetMapping("/viewReservations")
    public ResponseEntity<List<ReservationDTO>> viewReservations(@RequestParam int idHost){
        List<ReservationDTO> reservas = service.viewReservations(idHost);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /**
     * Obtiene el historial de reservas de un usuario.
     *
     * @param idUser ID del usuario.
     * @return ResponseEntity con lista de reservas o 204 No Content si no hay reservas.
     */
    @GetMapping("/viewReservationHistory")
    public ResponseEntity<List<ReservationDTO>> viewReservationHistory(@RequestParam int idUser){
        List<ReservationDTO> reservas = service.viewReservationHistory(idUser);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

}