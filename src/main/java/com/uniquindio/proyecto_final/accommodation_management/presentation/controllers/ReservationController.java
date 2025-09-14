package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/create/{idAccommodation}/{idHost}/{idUser}")
    public ResponseEntity<ReservationEntity> create(@RequestBody ReservationEntity reservation, @PathVariable int idAccommodation, @PathVariable int idHost, @PathVariable int idUser, BindingResult result){
        return null;
    }

    @PutMapping("/cancelReservations/{idReservation}")
    public ResponseEntity<ReservationEntity> cancelReservations(@PathVariable int idReservation, BindingResult result){
        return service.cancelReservations(idReservation);
    }

    @GetMapping("/viewReservationHistory/{idUser}")
    public ResponseEntity<List<AccommodationEntity>> viewReservationHistory(@PathVariable int idUser, BindingResult result){
        return service.viewReservationHistory(idUser);
    }

    @GetMapping("/viewReservationDetails/{idReservation}")
    public ResponseEntity<AccommodationEntity> viewAccommodationDetails(@PathVariable int idReservation, BindingResult result){
        return service.viewAccommodationDetails(idReservation);
    }

}
