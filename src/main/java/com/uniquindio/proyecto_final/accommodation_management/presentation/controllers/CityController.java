package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CityService;
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
 * Controlador REST para la entidad City.
 * Proporciona endpoints para crear, registrar, editar, eliminar y listar ciudades.
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

    // Inyección del servicio de ciudad
    @Autowired
    private CityService service;

    /**
     * Crea una nueva ciudad.
     * @param city DTO con los datos de la ciudad.
     * @param result resultados de validación.
     * @return ResponseEntity con ciudad creada o errores de validación.
     */
    // Endpoint POST para crear ciudad
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CityDTO city, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(city));
    }

    /**
     * Registra una ciudad (alias de create con validación @Valid).
     * @param city DTO de la ciudad.
     * @param result resultados de validación.
     * @return ResponseEntity con ciudad registrada o errores.
     */
    // Endpoint POST para registrar ciudad
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CityDTO city, BindingResult result){
        return create(city, result);
    }

    /**
     * Obtiene todas las ciudades activas.
     * @return lista de ciudades o 204 si no hay contenido.
     */
    // Endpoint GET para listar todas las ciudades
    @GetMapping("/getAll")
    public ResponseEntity<List<CityDTO>> citiesList(){
        List<CityDTO> todos = service.citiesList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Obtiene todas las ciudades de un departamento específico.
     * @param departmentId ID del departamento.
     * @return lista de ciudades o 204 si no hay contenido.
     */
    // Endpoint GET para listar ciudades por departamento
    @GetMapping("/getAllForDepartment")
    public ResponseEntity<List<CityDTO>> citiesListDepartment(@RequestParam("departmentId") int departmentId){
        List<CityDTO> todos = service.citiesListDepartment(departmentId);
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Obtiene detalle de una ciudad específica.
     * @param accommodationId ID de la ciudad.
     * @return ResponseEntity con la ciudad o 404 si no existe.
     */
    // Endpoint GET para detalle de ciudad
    @GetMapping("/getOne/{id}")
    public ResponseEntity<CityDTO> detail(@PathVariable("id") int accommodationId) {
        CityDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Edita una ciudad existente.
     * @param id ID de la ciudad.
     * @param user DTO con los datos a actualizar.
     * @param result resultados de validación.
     * @return ResponseEntity con ciudad actualizada, 404 o errores de validación.
     */
    // Endpoint PUT para editar ciudad
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody CityDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<CityDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Marca una ciudad como eliminada.
     * @param id ID de la ciudad.
     * @return ResponseEntity con ciudad eliminada o 404 si no existe.
     */
    // Endpoint PUT para eliminar ciudad
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<CityDTO> productOptional = service.delete(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
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