package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.DepartmentService;
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
 * Controlador REST para la entidad Department.
 * Proporciona endpoints para crear, registrar, editar, eliminar y listar departamentos.
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    // Inyección del servicio de departamentos
    @Autowired
    private DepartmentService service;

    /**
     * Crea un nuevo departamento.
     * @param departmentDTO DTO con los datos del departamento.
     * @param result resultados de validación.
     * @return ResponseEntity con departamento creado o errores de validación.
     */
    // Endpoint POST para crear departamento
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DepartmentDTO departmentDTO, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(departmentDTO));
    }

    /**
     * Registra un departamento (alias de create con validación @Valid).
     * @param department DTO con los datos del departamento.
     * @param result resultados de validación.
     * @return ResponseEntity con departamento registrado o errores de validación.
     */
    // Endpoint POST para registrar departamento
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody DepartmentDTO department, BindingResult result){
        return create(department, result);
    }

    /**
     * Lista todos los departamentos activos.
     * @return ResponseEntity con lista de departamentos o 204 si no hay contenido.
     */
    // Endpoint GET para listar todos los departamentos
    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentDTO>> departmentsList(){
        List<DepartmentDTO> todos = service.departmentsList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    /**
     * Obtiene detalle de un departamento específico.
     * @param accommodationId ID del departamento.
     * @return ResponseEntity con departamento o 404 si no existe.
     */
    // Endpoint GET para detalle de departamento
    @GetMapping("/getOne/{id}")
    public ResponseEntity<DepartmentDTO> detail(@PathVariable("id") int accommodationId) {
        DepartmentDTO detalle = service.detail(accommodationId);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    /**
     * Edita un departamento existente.
     * @param id ID del departamento.
     * @param user DTO con datos a actualizar.
     * @param result resultados de validación.
     * @return ResponseEntity con departamento actualizado, 404 o errores de validación.
     */
    // Endpoint PUT para editar departamento
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody DepartmentDTO user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<DepartmentDTO> userOptional = service.edit(id, user);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Marca un departamento como eliminado.
     * @param id ID del departamento.
     * @return ResponseEntity con departamento eliminado o 404 si no existe.
     */
    // Endpoint PUT para eliminar departamento
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<DepartmentDTO> productOptional = service.delete(id);

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