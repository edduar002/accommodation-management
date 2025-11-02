package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada
 * con los administradores del sistema.
 */
public interface AdministratorService {

    /**
     * Registra un nuevo administrador en el sistema.
     *
     * @param administrator Objeto DTO con los datos del administrador.
     * @return El administrador guardado con su ID correspondiente.
     */
    AdministratorDTO save(AdministratorDTO administrator);

    /**
     * Inicia sesión del administrador validando credenciales.
     *
     * @param login DTO que contiene email y contraseña.
     * @return El administrador autenticado junto con su token de sesión.
     */
    AdministratorDTO login(LoginDTO login);

    /**
     * Cambia la contraseña de un administrador.
     *
     * @param id ID del administrador.
     * @param user DTO con contraseña antigua y nueva.
     * @return Optional con el administrador actualizado si existe, vacío si no se encuentra.
     */
    Optional<AdministratorDTO> changePassword(int id, ChangePasswordDTO user);

    /**
     * Obtiene los detalles de un administrador.
     *
     * @param accommodation ID del administrador.
     * @return El DTO con la información del administrador.
     */
    AdministratorDTO detail(int accommodation);

    /**
     * Edita la información de un administrador.
     *
     * @param idHost ID del administrador a editar.
     * @param host DTO con los nuevos datos.
     * @return Optional con el administrador actualizado si fue encontrado, vacío si no.
     */
    Optional<AdministratorDTO> edit(int idHost, AdministratorDTO host);

}