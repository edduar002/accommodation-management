package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link UserDTO}.
 *
 * <p>Aplica reglas simples (editar nombre, cambio/recuperación de contraseña) y
 * delega persistencia/autenticación en {@link UserDAO}.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear usuarios: {@link #save(UserDTO)}.</li>
 *   <li>Login: {@link #login(LoginDTO)}.</li>
 *   <li>Editar nombre: {@link #edit(int, UserDTO)}.</li>
 *   <li>Cambiar contraseña: {@link #changePassword(int, ChangePasswordDTO)}.</li>
 *   <li>Recuperar contraseña: {@link #recoveryPassword(int, String)}.</li>
 * </ul>
 *
 * <p><b>Seguridad de logs:</b> nunca se registran contraseñas ni credenciales.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see UserDAO
 * @see UserService
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para usuarios (no nulo)
     */
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link UserDTO}.
     *
     * @param dto DTO del usuario (no nulo)
     * @return DTO persistido
     * @implSpec Delegado directo a {@link UserDAO#save(UserDTO)}.
     */
    @Override
    public UserDTO save(UserDTO dto) {
        log.debug("Guardando usuario (campos sensibles redacted): {}", dto);
        UserDTO saved = dao.save(dto);
        log.info("Usuario guardado: {}", saved);
        return saved;
    }

    /**
     * Autentica a un usuario.
     *
     * @param login credenciales (usuario/correo y contraseña)
     * @return {@link UserDTO} autenticado o {@code null} si no
     * @implSpec Delegado directo a {@link UserDAO#login(LoginDTO)}.
     */
    @Override
    public UserDTO login(LoginDTO login) {
        log.debug("Intento de login de usuario (credenciales redacted)");
        UserDTO result = dao.login(login);
        log.info("Resultado login usuario: {}", (result != null ? "OK" : "FALLÓ"));
        return result;
    }

    @Override
    public UserDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        UserDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    /**
     * Edita el nombre del usuario si existe.
     *
     * @param id identificador del usuario
     * @param user DTO con el nuevo nombre (se usa {@code getName()})
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no
     */
    @Transactional
    @Override
    public Optional<UserDTO> edit(int id, UserDTO user) {
        log.debug("Editando usuario id={} con newName={}", id, user.getName());
        Optional<UserDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            UserDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            UserDTO updated = dao.save(userNew);
            log.info("Usuario id={} actualizado (name)", id);
            return Optional.of(updated);
        }
        log.warn("No se encontró usuario id={} para editar", id);
        return userDb;
    }

    /**
     * Cambia la contraseña si la antigua coincide.
     *
     * @param id identificador del usuario
     * @param user DTO con contraseña antigua y nueva
     * @return {@link Optional} con el DTO actualizado si procede; vacío si no coincide o no existe
     */
    @Transactional
    @Override
    public Optional<UserDTO> changePassword(int id, ChangePasswordDTO user) {
        log.debug("changePassword userId={} (old/new redacted)", id);
        Optional<UserDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            UserDTO userNew = userDb.get();
            if (userNew.getPassword().equals(user.getOldPassword())) {
                userNew.setPassword(user.getNewPassword());
                dao.save(userNew);
                log.info("Contraseña actualizada para userId={}", id);
                return Optional.of(userNew);
            } else {
                log.warn("Contraseña antigua no coincide para userId={}", id);
                return Optional.empty();
            }
        }
        log.warn("No se encontró usuario id={} para changePassword", id);
        return Optional.empty();
    }

    /**
     * Recupera la contraseña estableciendo un nuevo valor si el usuario existe.
     *
     * @param id identificador del usuario
     * @param newPassword nueva contraseña (no se registra en logs)
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no existe
     */
    @Transactional
    @Override
    public Optional<UserDTO> recoveryPassword(int id, String newPassword) {
        log.debug("recoveryPassword userId={} (newPassword redacted)", id);
        Optional<UserDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            UserDTO userNew = userDb.get();
            userNew.setPassword(newPassword);
            dao.save(userNew);
            log.info("Contraseña recuperada/actualizada para userId={}", id);
            return Optional.of(userNew);
        }
        log.warn("No se encontró usuario id={} para recoveryPassword", id);
        return Optional.empty();
    }

    @Override
    public List<UserDTO> usersList() {
        log.debug("Buscando todas las ciudades");
        List<UserDTO> list = dao.usersList();
        log.info("Encontrados {} ciudades", list.size());
        return list;
    }

    @Transactional
    @Override
    public Optional<UserDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<UserDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            UserDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            UserDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }
}
