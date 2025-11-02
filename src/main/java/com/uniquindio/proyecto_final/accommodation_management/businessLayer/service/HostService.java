package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar los hosts dentro del sistema.
 */
public interface HostService {

    /**
     * Guarda un nuevo host en el sistema.
     *
     * @param host DTO del host a guardar.
     * @return DTO persistido con su ID asignado.
     */
    HostDTO save(HostDTO host);

    /**
     * Edita los datos de un host existente.
     *
     * @param idHost ID del host a editar.
     * @param host DTO con los datos actualizados.
     * @return Optional con el DTO actualizado si existe; vacío si no.
     */
    Optional<HostDTO> edit(int idHost, HostDTO host);

    /**
     * Cambia la contraseña de un host si la antigua coincide.
     *
     * @param id ID del host.
     * @param user DTO con la contraseña antigua y nueva.
     * @return Optional con el DTO actualizado si procede; vacío si no coincide o no existe.
     */
    Optional<HostDTO> changePassword(int id, ChangePasswordDTO user);

    /**
     * Consulta el detalle de un host por su ID.
     *
     * @param accommodation ID del host.
     * @return DTO del host si existe; null si no.
     */
    HostDTO detail(int accommodation);

    /**
     * Autentica a un host con login (email y contraseña).
     *
     * @param login DTO con credenciales.
     * @return DTO del host autenticado.
     */
    HostDTO login(LoginDTO login);

    /**
     * Recupera la contraseña de un host estableciendo un nuevo valor.
     *
     * @param id ID del host.
     * @param newPassword nueva contraseña.
     * @return Optional con el DTO actualizado si existe; vacío si no.
     */
    Optional<HostDTO> recoveryPassword(int id, String newPassword);

    /**
     * Lista todos los hosts registrados.
     *
     * @return Lista de DTOs de hosts.
     */
    List<HostDTO> hostsList();

    /**
     * Inactiva (soft delete) un host por su ID.
     *
     * @param idAccommodation ID del host a inactivar.
     * @return Optional con el DTO inactivado si existe; vacío si no.
     */
    Optional<HostDTO> delete(int idAccommodation);
}