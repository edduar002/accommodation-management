package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
@RequestMapping("/api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccommodationEntity acommodation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(acommodation));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccommodationEntity acommodation, BindingResult result){
        return create(acommodation, result);
    }

    @GetMapping("/searchAvailableAccommodations")
    public ResponseEntity<List<AccommodationEntity>> searchAvailableAccommodations() {
        List<AccommodationEntity> disponibles = service.searchAvailableAccommodations();
        if (disponibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(disponibles);
    }

    @GetMapping("/ownAccommodationList")
    public ResponseEntity<List<AccommodationEntity>> ownAccommodationList(@RequestParam int idHost){
        List<AccommodationEntity> propios = service.ownAccommodationList(idHost);
        if (propios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propios);
    }

    @PutMapping("/edit")
    public ResponseEntity<AccommodationEntity> edit(@RequestParam int idAccommodation, BindingResult result){
        return service.edit(idAccommodation);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccommodationEntity> delete(@RequestParam int idAccommodation, BindingResult result){
        return service.delete(idAccommodation);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AccommodationEntity> detail(@PathVariable("id") int accommodationId) {
        AccommodationEntity detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("viewMetrics")
    public ResponseEntity<AccommodationEntity> viewMetrics(@RequestParam int accommodation, BindingResult result){
        return service.viewMetrics(accommodation);
    }

    @PutMapping("/acceptReservationRequests")
    public ResponseEntity<AccommodationEntity> acceptReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.acceptReservationRequests(idAccommodation);
    }

    @PutMapping("/rejectReservationRequests")
    public ResponseEntity<AccommodationEntity> rejectReservationRequests(@RequestParam int idAccommodation, BindingResult result){
        return service.rejectReservationRequests(idAccommodation);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @GetMapping("/averageGrades/{idAccommodation}")
    public ResponseEntity<Double> averageGrades(@PathVariable("idAccommodation") int idAccommodation){
        Double calificaciones = service.averageGrades(idAccommodation);
        return ResponseEntity.ok(calificaciones);
    }

}
