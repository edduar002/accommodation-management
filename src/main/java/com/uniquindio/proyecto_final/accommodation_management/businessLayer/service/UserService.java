package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar usuarios de la aplicación.
 *
 * <p>Se encarga de operaciones como creación, edición, eliminación,
 * autenticación y recuperación de contraseña.</p>
 */
public interface UserService {

    /**
     * Persiste un usuario.
     * @param user DTO del usuario (no nulo)
     * @return DTO persistido con id asignado
     */
    UserDTO save(UserDTO user);

    /**
     * Autentica un usuario con credenciales.
     * @param login DTO con email y password
     * @return DTO del usuario autenticado con token JWT
     */
    UserDTO login(LoginDTO login);

    /**
     * Edita los datos de un usuario existente.
     * @param idAccommodation id del usuario a editar
     * @param user DTO con los datos actualizados
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no
     */
    Optional<UserDTO> edit(int idAccommodation, UserDTO user);

    /**
     * Cambia la contraseña de un usuario si la antigua coincide.
     * @param id id del usuario
     * @param user DTO con contraseña antigua y nueva
     * @return {@link Optional} con DTO actualizado si coincide; vacío si no
     */
    Optional<UserDTO> changePassword(int id, ChangePasswordDTO user);

    /**
     * Recupera la contraseña de un usuario estableciendo un nuevo valor.
     * @param id id del usuario
     * @param newPassword nueva contraseña
     * @return {@link Optional} con DTO actualizado si el usuario existe; vacío si no
     */
    Optional<UserDTO> recoveryPassword(int id, String newPassword);

    /**
     * Lista todos los usuarios.
     * @return lista de usuarios (posiblemente vacía)
     */
    List<UserDTO> usersList();

    /**
     * Consulta detalle de un usuario por id.
     * @param accommodation id del usuario
     * @return DTO si existe; {@code null} si no
     */
    UserDTO detail(int accommodation);

    /**
     * Elimina (soft delete) un usuario.
     * @param idAccommodation id del usuario a eliminar
     * @return {@link Optional} con DTO inactivado si existe; vacío si no
     */
    Optional<UserDTO> delete(int idAccommodation);
}