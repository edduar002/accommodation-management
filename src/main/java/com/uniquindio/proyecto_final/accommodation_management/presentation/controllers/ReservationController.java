package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationDTO reservation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservation));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ReservationDTO reservation, BindingResult result){
        return create(reservation, result);
    }

    @PutMapping("/cancelReservations/{idReservation}")
    public ResponseEntity<ReservationDTO> cancelReservations(@PathVariable int idReservation, BindingResult result){
        return service.cancelReservations(idReservation);
    }

    @GetMapping("/viewReservationDetails/{idAccommodation}")
    public ResponseEntity<ReservationDTO> viewReservationDetails(@PathVariable("idAccommodation") int accommodationId) {
        ReservationDTO detalle = service.viewReservationDetails(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @GetMapping("/viewAccommodationReservations/{idAccommodation}")
    public ResponseEntity<List<ReservationDTO>> viewAccommodationReservations(@PathVariable("idAccommodation") int idAccommodation){
        List<ReservationDTO> reservas = service.viewAccommodationReservations(idAccommodation);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/viewReservationHistory")
    public ResponseEntity<List<ReservationDTO>> viewReservationHistory(@RequestParam int idUser){
        List<ReservationDTO> reservas = service.viewReservationHistory(idUser);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/acceptReservationRequests")
    public ResponseEntity<ReservationDTO> acceptReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.acceptReservationRequests(idAccommodation);
    }

    @PutMapping("/rejectReservationRequests")
    public ResponseEntity<ReservationDTO> rejectReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.rejectReservationRequests(idAccommodation);
    }

}
