package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

// IMPORTACIONES PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;      // Para documentar cada método
import io.swagger.v3.oas.annotations.Parameter;      // Para documentar parámetros
import io.swagger.v3.oas.annotations.responses.ApiResponse;      // Para documentar respuestas
import io.swagger.v3.oas.annotations.responses.ApiResponses;     // Para múltiples respuestas
import io.swagger.v3.oas.annotations.tags.Tag;       // Para agrupar endpoints

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation){
        return service.save(reservation);
    }

    @PostMapping
    public ResponseEntity<Reservation> makeReservations(@RequestParam LocalDate checkIn, @RequestParam LocalDate checkOut){
        return service.makeReservations(checkIn, checkOut);
    }

    @PutMapping
    public ResponseEntity<Reservation> cancelReservations(@RequestParam int idReservation){
        return service.cancelReservations(idReservation);
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> viewReservationHistory(@RequestParam int idUser){
        return service.viewReservationHistory(idUser);
    }

    @GetMapping
    public ResponseEntity<Accommodation> viewAccommodationDetails(@RequestParam int idAccommodation){
        return service.viewAccommodationDetails(idAccommodation);
    }

}
