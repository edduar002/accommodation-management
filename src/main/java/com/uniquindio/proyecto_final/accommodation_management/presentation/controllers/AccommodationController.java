package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

// IMPORTACIONES PARA SWAGGER

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RestController
@RequestMapping("/api/accomodations")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping("/create")
    public ResponseEntity<AccommodationEntity> create(@RequestBody AccommodationEntity accommodation, BindingResult result){
        return service.save(accommodation);
    }

    @GetMapping("/searchAvailableAccommodations")
    public ResponseEntity<List<AccommodationEntity>> searchAvailableAccommodations(@RequestParam int ciudad, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, BindingResult result){
        return service.searchAvailableAccommodations(ciudad, fechaInicio, fechaFin);
    }

    @GetMapping("/ownAccommodationList")
    public ResponseEntity<List<AccommodationEntity>> ownAccommodationList(@RequestParam int idHost, BindingResult result){
        return service.ownAccommodationList(idHost);
    }

    @PutMapping("/edit")
    public ResponseEntity<AccommodationEntity> edit(@RequestParam int idAccommodation, BindingResult result){
        return service.edit(idAccommodation);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccommodationEntity> delete(@RequestParam int idAccommodation, BindingResult result){
        return service.delete(idAccommodation);
    }

    @GetMapping("/detail")
    public ResponseEntity<AccommodationEntity> detail(@RequestParam int accommodation, BindingResult result){
        return service.detail(accommodation);
    }

    @GetMapping("viewMetrics")
    public ResponseEntity<AccommodationEntity> viewMetrics(@RequestParam int accommodation, BindingResult result){
        return service.viewMetrics(accommodation);
    }

    @GetMapping("/viewAccommodationReservations")
    public ResponseEntity<List<AccommodationEntity>> viewAccommodationReservations(@RequestParam int idReservation, BindingResult result){
        return service.viewAccommodationReservations(idReservation);
    }

    @PutMapping("/acceptReservationRequests")
    public ResponseEntity<AccommodationEntity> acceptReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.acceptReservationRequests(idAccommodation);
    }

    @PutMapping("/rejectReservationRequests")
    public ResponseEntity<AccommodationEntity> rejectReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.rejectReservationRequests(idAccommodation);
    }

    @GetMapping("/commentsList")
    public ResponseEntity<List<CommentEntity>> commentsList(@RequestParam int idAccommodation, BindingResult result){
        return service.commentsList(idAccommodation);
    }

    @GetMapping("/averageGrades")
    public ResponseEntity<List<QualificationEntity>> averageGrades(@RequestParam int idAccommodation, BindingResult result){
        return service.averageGrades(idAccommodation);
    }

}
