package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada
 * con los alojamientos (Accommodations).
 */
public interface AccommodationService {

    /**
     * Guarda un nuevo alojamiento en el sistema.
     *
     * @param accommodation Objeto DTO con los datos del alojamiento.
     * @return El alojamiento registrado, incluyendo ID generado.
     */
    AccommodationDTO save(AccommodationDTO accommodation);

    /**
     * Obtiene el promedio de calificaciones de un alojamiento específico.
     *
     * @param accommodationId ID del alojamiento.
     * @return Promedio de calificaciones como Double.
     */
    Double getAverageCalification(int accommodationId);

    /**
     * Busca y retorna todos los alojamientos que se encuentran disponibles.
     * Dependiendo de tu lógica, esto generalmente significa:
     * - active = true
     * - estado libre / no reservado actualmente.
     *
     * @return Lista de alojamientos disponibles.
     */
    List<AccommodationDTO> searchAvailableAccommodations();

    /**
     * Lista todos los alojamientos pertenecientes a un anfitrión.
     *
     * @param idHost ID del host propietario.
     * @return Lista de alojamientos registrados por el host.
     */
    List<AccommodationDTO> ownAccommodationList(int idHost);

    /**
     * Edita la información de un alojamiento existente.
     *
     * @param idAccommodation ID del alojamiento a editar.
     * @param accommodation Objeto DTO con los datos actualizados.
     * @return Optional con el alojamiento actualizado si existe, vacío en caso contrario.
     */
    Optional<AccommodationDTO> edit(int idAccommodation, AccommodationDTO accommodation);

    /**
     * Elimina (o desactiva) un alojamiento del sistema.
     * Dependiendo del diseño, esta operación puede ser un soft-delete.
     *
     * @param idAccommodation ID del alojamiento a eliminar.
     * @return Optional con el alojamiento eliminado/desactivado si existe, vacío si no.
     */
    Optional<AccommodationDTO> delete(int idAccommodation);

    /**
     * Obtiene el detalle completo de un alojamiento.
     *
     * @param accommodation ID del alojamiento a consultar.
     * @return El detalle del alojamiento.
     */
    AccommodationDTO detail(int accommodation);
}