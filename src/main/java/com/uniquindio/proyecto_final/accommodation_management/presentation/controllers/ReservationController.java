package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationEntity reservation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservation));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ReservationEntity reservation, BindingResult result){
        return create(reservation, result);
    }

    @PutMapping("/cancelReservations/{idReservation}")
    public ResponseEntity<ReservationEntity> cancelReservations(@PathVariable int idReservation, BindingResult result){
        return service.cancelReservations(idReservation);
    }

    @GetMapping("/viewReservationHistory/{idUser}")
    public ResponseEntity<List<AccommodationEntity>> viewReservationHistory(@PathVariable int idUser, BindingResult result){
        return service.viewReservationHistory(idUser);
    }

    @GetMapping("/viewReservationDetails/{idAccommodation}")
    public ResponseEntity<AccommodationEntity> viewAccommodationDetails(@PathVariable int idAccommodation){
        return service.viewAccommodationDetails(idAccommodation);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @GetMapping("/viewAccommodationReservations/{idAccommodation}")
    public ResponseEntity<List<ReservationEntity>> viewAccommodationReservations(@PathVariable("idAccommodation") int idAccommodation){
        List<ReservationEntity> reservas = service.viewAccommodationReservations(idAccommodation);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

}
