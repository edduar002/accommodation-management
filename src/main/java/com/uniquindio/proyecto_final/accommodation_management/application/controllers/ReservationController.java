package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Image;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
