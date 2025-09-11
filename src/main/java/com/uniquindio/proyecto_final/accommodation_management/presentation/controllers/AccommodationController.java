package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/accomodations")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping("/create")
    public ResponseEntity<AccommodationEntity> create(@RequestBody AccommodationEntity accommodation){
        return service.save(accommodation);
    }

    @GetMapping("/searchAvailableAccommodations")
    public ResponseEntity<List<AccommodationEntity>> searchAvailableAccommodations(@RequestParam int ciudad, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        return service.searchAvailableAccommodations(ciudad, fechaInicio, fechaFin);
    }

    @GetMapping("/ownAccommodationList")
    public ResponseEntity<List<AccommodationEntity>> ownAccommodationList(@RequestParam int idHost){
        return service.ownAccommodationList(idHost);
    }

    @PutMapping("/edit")
    public ResponseEntity<AccommodationEntity> edit(@RequestParam int idAccommodation){
        return service.edit(idAccommodation);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccommodationEntity> delete(@RequestParam int idAccommodation){
        return service.delete(idAccommodation);
    }

    @GetMapping("/detail")
    public ResponseEntity<AccommodationEntity> detail(@RequestParam int accommodation){
        return service.detail(accommodation);
    }

    @GetMapping("viewMetrics")
    public ResponseEntity<AccommodationEntity> viewMetrics(@RequestParam int accommodation){
        return service.viewMetrics(accommodation);
    }

    @GetMapping("/viewAccommodationReservations")
    public ResponseEntity<List<AccommodationEntity>> viewAccommodationReservations(@RequestParam int idReservation){
        return service.viewAccommodationReservations(idReservation);
    }

    @PutMapping("/acceptReservationRequests")
    public ResponseEntity<AccommodationEntity> acceptReservationRequests(@RequestParam int idAccommodation){
        return service.acceptReservationRequests(idAccommodation);
    }

    @PutMapping("/rejectReservationRequests")
    public ResponseEntity<AccommodationEntity> rejectReservationRequests(@RequestParam int idAccommodation){
        return service.rejectReservationRequests(idAccommodation);
    }

    @GetMapping("/commentsList")
    public ResponseEntity<List<CommentEntity>> commentsList(@RequestParam int idAccommodation){
        return service.commentsList(idAccommodation);
    }

    @GetMapping("/averageGrades")
    public ResponseEntity<List<QualificationEntity>> averageGrades(@RequestParam int idAccommodation){
        return service.averageGrades(idAccommodation);
    }

}
