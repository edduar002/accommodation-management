package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
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
@RequestMapping("/api/accomodations")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping
    public ResponseEntity<Accommodation> create(@RequestBody Accommodation accommodation){
        return service.save(accommodation);
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> searchAvailableAccommodations(@RequestParam int ciudad, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        return service.searchAvailableAccommodations(ciudad, fechaInicio, fechaFin);
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> ownAccommodationList(@RequestParam int idHost){
        return service.ownAccommodationList(idHost);
    }

    @PutMapping
    public ResponseEntity<Accommodation> edit(@RequestParam int idAccommodation){
        return service.edit(idAccommodation);
    }

    @DeleteMapping
    public ResponseEntity<Accommodation> delete(@RequestParam int idAccommodation){
        return service.delete(idAccommodation);
    }

    @GetMapping
    public ResponseEntity<Accommodation> detail(@RequestParam int accommodation){
        return service.detail(accommodation);
    }

    @GetMapping
    public ResponseEntity<Accommodation> viewMetrics(@RequestParam int accommodation){
        return service.viewMetrics(accommodation);
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> viewAccommodationReservations(@RequestParam int idReservation){
        return service.viewAccommodationReservations(idReservation);
    }

    @PutMapping
    public ResponseEntity<Accommodation> acceptReservationRequests(@RequestParam int idAccommodation){
        return service.acceptReservationRequests(idAccommodation);
    }

    @PutMapping
    public ResponseEntity<Accommodation> rejectReservationRequests(@RequestParam int idAccommodation){
        return service.rejectReservationRequests(idAccommodation);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> commentsList(@RequestParam int idAccommodation){
        return service.commentsList(idAccommodation);
    }

    @GetMapping
    public ResponseEntity<List<Qualification>> averageGrades(@RequestParam int idAccommodation){
        return service.averageGrades(idAccommodation);
    }

}
