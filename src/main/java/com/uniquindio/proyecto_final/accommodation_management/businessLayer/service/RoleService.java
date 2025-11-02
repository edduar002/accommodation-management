package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar roles de usuarios/hosts.
 */
public interface RoleService {

    /**
     * Persiste un rol.
     * @param role DTO del rol (no nulo)
     * @return DTO persistido
     */
    RoleDTO save(RoleDTO role);

    /**
     * Lista todos los roles existentes.
     * @return lista de roles (posiblemente vacía)
     */
    List<RoleDTO> rolesList();

    /**
     * Consulta el detalle de un rol por id.
     * @param accommodation id del rol
     * @return DTO si existe; {@code null} si no
     */
    RoleDTO detail(int accommodation);

    /**
     * Edita un rol existente.
     * @param idAccommodation id del rol a editar
     * @param user DTO con los datos actualizados
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no
     */
    Optional<RoleDTO> edit(int idAccommodation, RoleDTO user);

    /**
     * Elimina (soft delete) un rol.
     * @param idAccommodation id del rol a eliminar
     * @return {@link Optional} con el DTO inactivado si existe; vacío si no
     */
    Optional<RoleDTO> delete(int idAccommodation);
}