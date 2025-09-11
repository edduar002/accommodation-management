package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/create")
    public ResponseEntity<ReservationEntity> create(@RequestBody ReservationEntity reservation){
        return service.save(reservation);
    }

    @PostMapping("/makeReservations")
    public ResponseEntity<ReservationEntity> makeReservations(@RequestParam LocalDate checkIn, @RequestParam LocalDate checkOut){
        return service.makeReservations(checkIn, checkOut);
    }

    @PutMapping("/cancelReservations")
    public ResponseEntity<ReservationEntity> cancelReservations(@RequestParam int idReservation){
        return service.cancelReservations(idReservation);
    }

    @GetMapping("/viewReservationHistory")
    public ResponseEntity<List<AccommodationEntity>> viewReservationHistory(@RequestParam int idUser){
        return service.viewReservationHistory(idUser);
    }

    @GetMapping("/viewAccommodationDetails")
    public ResponseEntity<AccommodationEntity> viewAccommodationDetails(@RequestParam int idAccommodation){
        return service.viewAccommodationDetails(idAccommodation);
    }

}
