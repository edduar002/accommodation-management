package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.DepartmentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// IMPORTACIONES PARA SWAGGER

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DepartmentDTO departmentDTO, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(departmentDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody DepartmentDTO department, BindingResult result){
        return create(department, result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentDTO>> departmentsList(){
        List<DepartmentDTO> todos = service.departmentsList();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " no puede ser vacio " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
