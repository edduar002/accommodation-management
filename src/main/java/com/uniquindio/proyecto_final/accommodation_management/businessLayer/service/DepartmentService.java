package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar los departamentos dentro del sistema.
 */
public interface DepartmentService {

    /**
     * Guarda un nuevo departamento en el sistema.
     *
     * @param department DTO del departamento a guardar.
     * @return DTO persistido con su ID asignado.
     */
    DepartmentDTO save(DepartmentDTO department);

    /**
     * Lista todos los departamentos registrados.
     *
     * @return Lista de DTOs de departamentos.
     */
    List<DepartmentDTO> departmentsList();

    /**
     * Consulta el detalle de un departamento por su ID.
     *
     * @param accommodation ID del departamento.
     * @return DTO del departamento si existe; null si no.
     */
    DepartmentDTO detail(int accommodation);

    /**
     * Edita los datos de un departamento existente.
     *
     * @param idAccommodation ID del departamento a editar.
     * @param user DTO con los datos actualizados.
     * @return Optional con el DTO actualizado si existe; vacío si no.
     */
    Optional<DepartmentDTO> edit(int idAccommodation, DepartmentDTO user);

    /**
     * Inactiva (soft delete) un departamento por su ID.
     *
     * @param idAccommodation ID del departamento a inactivar.
     * @return Optional con el DTO inactivado si existe; vacío si no.
     */
    Optional<DepartmentDTO> delete(int idAccommodation);
}