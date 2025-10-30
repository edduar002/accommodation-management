package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.HostDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para la gestión de {@link HostDTO}.
 *
 * <p>Aplica reglas simples (edición de nombre, cambio/recuperación de contraseña) y
 * delega la persistencia y autenticación en {@link HostDAO}.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear anfitriones: {@link #save(HostDTO)}.</li>
 *   <li>Editar nombre: {@link #edit(int, HostDTO)}.</li>
 *   <li>Cambiar contraseña: {@link #changePassword(int, ChangePasswordDTO)}.</li>
 *   <li>Login: {@link #login(LoginDTO)}.</li>
 *   <li>Recuperar contraseña: {@link #recoveryPassword(int, String)}.</li>
 * </ul>
 *
 * <p><b>Seguridad de logs:</b> nunca se registran contraseñas ni credenciales en claro.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see HostDAO
 * @see HostService
 */
@Slf4j
@Service
public class HostServiceImpl implements HostService {

    private final HostDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para {@link HostDTO} (no nulo)
     */
    public HostServiceImpl(HostDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link HostDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring.</p>
     *
     * @param dto DTO del anfitrión a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link HostDAO#save(HostDTO)}.
     */
    @Override
    @Transactional
    public HostDTO save(HostDTO dto) {
        log.debug("Guardando host (campos sensibles redacted)");
        HostDTO saved = dao.save(dto);
        log.info("Host guardado");
        return saved;
    }

    @Override
    public HostDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        HostDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    /**
     * Edita el nombre del anfitrión si existe.
     *
     * @param idHost identificador del anfitrión
     * @param host DTO con el nuevo nombre (se usa {@code getName()})
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no existe
     * @implSpec
     * <ul>
     *   <li>Busca por ID; si existe, copia {@code name} y guarda.</li>
     * </ul>
     */
    @Override
    @Transactional
    public Optional<HostDTO> edit(int idHost, HostDTO host) {
        log.debug("Editando host id={} con newName={}", idHost, host.getName());
        Optional<HostDTO> hostDb = dao.findById(idHost);
        if (hostDb.isPresent()) {
            HostDTO hostNew = hostDb.orElseThrow();
            hostNew.setDepartmentsId(host.getDepartmentsId());
            hostNew.setCitiesId(host.getCitiesId());
            hostNew.setName(host.getName());
            hostNew.setEmail(host.getEmail());
            hostNew.setImgUrl(host.getImgUrl());
            hostNew.setSurname(host.getSurname());
            HostDTO updated = dao.save(hostNew);
            log.info("Host id={} actualizado (name)", idHost);
            return Optional.of(updated);
        }
        log.warn("No se encontró host id={} para editar", idHost);
        return Optional.empty();
    }

    /**
     * Cambia la contraseña si la antigua coincide.
     *
     * @param id identificador del anfitrión
     * @param user DTO con contraseña antigua y nueva
     * @return {@link Optional} con el DTO actualizado si procede; vacío si no coincide o no existe
     * @implSpec
     * <ul>
     *   <li>Busca por ID; si existe y {@code oldPassword} coincide, cambia a {@code newPassword} y guarda.</li>
     * </ul>
     */
    @Transactional
    @Override
    public Optional<HostDTO> changePassword(int id, ChangePasswordDTO user) {
        log.debug("changePassword hostId={} (old/new redacted)", id);
        Optional<HostDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            HostDTO userNew = userDb.get();
            if (userNew.getPassword().equals(user.getOldPassword())) {
                userNew.setPassword(user.getNewPassword());
                dao.save(userNew);
                log.info("Contraseña actualizada para hostId={}", id);
                return Optional.of(userNew);
            } else {
                log.warn("Contraseña antigua no coincide para hostId={}", id);
                return Optional.empty();
            }
        }
        log.warn("No se encontró host id={} para changePassword", id);
        return Optional.empty();
    }

    /**
     * Auténtica a un anfitrión usando las credenciales provistas.
     *
     * @param login credenciales (usuario/correo y contraseña)
     * @return {@link HostDTO} autenticado si procede; puede ser {@code null} si no
     * @implSpec Delegado directo a {@link HostDAO#login(LoginDTO)}.
     */
    @Override
    public HostDTO login(LoginDTO login) {
        log.debug("Intento de login host (credenciales redacted)");
        HostDTO result = dao.login(login);
        log.info("Resultado login host: {}", (result != null ? "OK" : "FALLÓ"));
        return result;
    }

    /**
     * Recupera la contraseña estableciendo un nuevo valor si el host existe.
     *
     * @param id identificador del anfitrión
     * @param newPassword nueva contraseña (no se registra en logs)
     * @return {@link Optional} con el DTO actualizado si existe; vacío si no existe
     * @implSpec
     * <ul>
     *   <li>Busca por ID; si existe, asigna {@code newPassword} y guarda.</li>
     * </ul>
     */
    @Transactional
    @Override
    public Optional<HostDTO> recoveryPassword(int id, String newPassword) {
        log.debug("recoveryPassword hostId={} (newPassword redacted)", id);
        Optional<HostDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            HostDTO userNew = userDb.get();
            userNew.setPassword(newPassword);
            dao.save(userNew);
            log.info("Contraseña recuperada/actualizada para hostId={}", id);
            return Optional.of(userNew);
        }
        log.warn("No se encontró host id={} para recoveryPassword", id);
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<HostDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<HostDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            HostDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            HostDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }

    @Override
    public List<HostDTO> hostsList() {
        log.debug("Buscando todas las ciudades");
        List<HostDTO> list = dao.hostsList();
        log.info("Encontrados {} ciudades", list.size());
        return list;
    }
}
