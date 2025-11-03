package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
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

/**
 * Controlador REST para la entidad Accommodation.
 * Proporciona endpoints para crear, editar, eliminar, listar y buscar alojamientos.
 */
@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    // Inyección del servicio de alojamiento
    @Autowired
    private AccommodationService service;

    /**
     * Crea un nuevo alojamiento.
     * @param acommodation DTO con los datos del alojamiento.
     * @param result resultados de validación.
     * @return ResponseEntity con el alojamiento creado o errores de validación.
     */
    // Endpoint POST para crear alojamiento
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccommodationDTO acommodation, BindingResult result){
        // Validación de campos
        if(result.hasFieldErrors()){
            return validation(result);
        }
        // Retorna alojamiento creado con código 201
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(acommodation));
    }

    /**
     * Registra un nuevo alojamiento (alias de create con validación @Valid).
     * @param acommodation DTO del alojamiento.
     * @param result resultados de validación.
     * @return ResponseEntity con el alojamiento registrado o errores.
     */
    // Endpoint POST para registrar alojamiento
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccommodationDTO acommodation, BindingResult result){
        return create(acommodation, result);
    }

    /**
     * Lista todos los alojamientos disponibles.
     * @return lista de alojamientos disponibles o 204 si no hay contenido.
     */
    // Endpoint GET para buscar alojamientos disponibles
    @GetMapping("/searchAvailableAccommodations")
    public ResponseEntity<List<AccommodationDTO>> searchAvailableAccommodations() {
        List<AccommodationDTO> disponibles = service.searchAvailableAccommodations();
        if (disponibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(disponibles);
    }

    /**
     * Lista los alojamientos de un host específico.
     * @param idHost ID del host.
     * @return lista de alojamientos del host o 204 si no hay contenido.
     */
    // Endpoint GET para obtener lista de alojamientos propios
    @GetMapping("/ownAccommodationList")
    public ResponseEntity<List<AccommodationDTO>> ownAccommodationList(@RequestParam int idHost){
        List<AccommodationDTO> propios = service.ownAccommodationList(idHost);
        if (propios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propios);
    }

    // Endpoint GET para obtener el promedio de calificaciones de un alojamiento
    @GetMapping("/averageCalification/{id}")
    public ResponseEntity<Double> getAverageCalification(@PathVariable("id") int accommodationId) {
        try {
            Double average = service.getAverageCalification(accommodationId);
            return ResponseEntity.ok(average);
        } catch (Exception e) {
            // Si ocurre algún error, devolvemos 500 y mensaje en consola
            System.err.println("Error al obtener promedio de calificaciones: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0.0);
        }
    }


    /**
     * Edita un alojamiento existente.
     * @param id ID del alojamiento.
     * @param accommodation DTO con los datos a actualizar.
     * @param result resultados de validación.
     * @return ResponseEntity con alojamiento actualizado, 404 o errores de validación.
     */
    // Endpoint PUT para editar alojamiento
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

    /**
     * Marca un alojamiento como eliminado.
     * @param id ID del alojamiento.
     * @return ResponseEntity con alojamiento eliminado o 404 si no existe.
     */
    // Endpoint PUT para "eliminar" alojamiento
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<AccommodationDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Obtiene detalle de un alojamiento específico.
     * @param accommodationId ID del alojamiento.
     * @return ResponseEntity con el alojamiento o 404 si no existe.
     */
    // Endpoint GET para detalle de alojamiento
    @GetMapping("/detail/{id}")
    public ResponseEntity<AccommodationDTO> detail(@PathVariable("id") int accommodationId) {
        AccommodationDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Valida los campos de un BindingResult y retorna errores en formato JSON.
     * @param result BindingResult con errores de validación.
     * @return ResponseEntity con errores y status 400.
     */
    // Método privado para manejar errores de validación
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            // Genera mensaje de error para cada campo
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}