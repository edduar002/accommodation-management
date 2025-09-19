package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// IMPORTACIONES PARA SWAGGER


@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccommodationDTO acommodation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(acommodation));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccommodationDTO acommodation, BindingResult result){
        return create(acommodation, result);
    }

    @GetMapping("/searchAvailableAccommodations")
    public ResponseEntity<List<AccommodationDTO>> searchAvailableAccommodations() {
        List<AccommodationDTO> disponibles = service.searchAvailableAccommodations();
        if (disponibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(disponibles);
    }

    @GetMapping("/ownAccommodationList")
    public ResponseEntity<List<AccommodationDTO>> ownAccommodationList(@RequestParam int idHost){
        List<AccommodationDTO> propios = service.ownAccommodationList(idHost);
        if (propios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propios);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id,  @RequestBody AccommodationDTO accommodation, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<AccommodationDTO> productOptional = service.edit(id, accommodation);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<AccommodationDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AccommodationDTO> detail(@PathVariable("id") int accommodationId) {
        AccommodationDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("viewMetrics")
    public ResponseEntity<AccommodationDTO> viewMetrics(@RequestParam int accommodation, BindingResult result){
        return service.viewMetrics(accommodation);
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
