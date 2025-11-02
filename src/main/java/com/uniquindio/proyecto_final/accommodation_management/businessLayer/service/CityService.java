package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada
 * con las ciudades y su administración.
 */
public interface CityService {

    /**
     * Guarda una nueva ciudad en el sistema.
     *
     * @param city DTO de la ciudad a guardar.
     * @return DTO persistido con su ID asignado.
     */
    CityDTO save(CityDTO city);

    /**
     * Lista todas las ciudades registradas.
     *
     * @return Lista de DTOs de ciudades, posiblemente vacía.
     */
    List<CityDTO> citiesList();

    /**
     * Lista las ciudades pertenecientes a un departamento específico.
     *
     * @param id ID del departamento.
     * @return Lista de DTOs de ciudades filtradas por departamento.
     */
    List<CityDTO> citiesListDepartment(int id);

    /**
     * Obtiene el detalle de una ciudad.
     *
     * @param accommodation ID de la ciudad (se mantiene nombre por consistencia con otros servicios).
     * @return DTO de la ciudad si existe.
     */
    CityDTO detail(int accommodation);

    /**
     * Edita la información de una ciudad.
     *
     * @param idAccommodation ID de la ciudad a editar.
     * @param user DTO con los nuevos datos.
     * @return Optional con la ciudad actualizada si fue encontrada, vacío si no existe.
     */
    Optional<CityDTO> edit(int idAccommodation, CityDTO user);

    /**
     * Elimina (o inactiva) una ciudad.
     *
     * @param idAccommodation ID de la ciudad.
     * @return Optional con la ciudad inactivada si fue encontrada, vacío si no existe.
     */
    Optional<CityDTO> delete(int idAccommodation);
}