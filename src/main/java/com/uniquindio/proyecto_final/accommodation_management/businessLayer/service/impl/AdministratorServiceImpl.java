package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AdministratorService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.validators.PasswordValidator;
import com.uniquindio.proyecto_final.accommodation_management.config.JwtConfig;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AdministratorDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementación del servicio de negocio para administrar usuarios de tipo
 * {@link AdministratorDTO}.
 *
 * <p>La clase delega la persistencia y autenticación en {@link AdministratorDAO}.
 * No agrega reglas adicionales: su responsabilidad es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear/actualizar administradores: {@link #save(AdministratorDTO)}.</li>
 *   <li>Iniciar sesión (autenticación): {@link #login(LoginDTO)}.</li>
 * </ul>
 *
 * <p><b>Seguridad de logs:</b> los mensajes de logging evitan exponer credenciales
 * o datos sensibles. Las credenciales se marcan como “redacted”.</p>
 *
 * @author
 *   Equipo Prg Avanzada
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see AdministratorDAO
 * @see AdministratorService
 */
@Slf4j
@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * Crea el servicio con su dependencia DAO.
     *
     * @param dao componente de acceso a datos para administradores (no nulo)
     */
    public AdministratorServiceImpl(AdministratorDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link AdministratorDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring.</p>
     *
     * @param dto DTO del administrador a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link AdministratorDAO#save(AdministratorDTO)}.
     */
    @Override
    @Transactional
    public AdministratorDTO save(AdministratorDTO dto) {
        PasswordValidator.validate(dto.getPassword());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        log.debug("Guardando administrador: {}", dto);
        AdministratorDTO saved = dao.save(dto);
        log.info("Administrador guardado: {}", saved);
        return saved;
    }

    @Transactional
    @Override
    public Optional<AdministratorDTO> changePassword(int id, ChangePasswordDTO user) {
        log.debug("changePassword userId={} (old/new redacted)", id);
        Optional<AdministratorDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            AdministratorDTO userNew = userDb.get();
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

    @Override
    public AdministratorDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        AdministratorDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    @Override
    @Transactional
    public Optional<AdministratorDTO> edit(int idHost, AdministratorDTO host) {
        log.debug("Editando host id={} con newName={}", idHost, host.getName());
        Optional<AdministratorDTO> hostDb = dao.findById(idHost);
        if (hostDb.isPresent()) {
            AdministratorDTO hostNew = hostDb.orElseThrow();
            hostNew.setName(host.getName());
            hostNew.setSurname(host.getSurname());
            hostNew.setEmail(host.getEmail());
            AdministratorDTO updated = dao.save(hostNew);
            log.info("Host id={} actualizado (name)", idHost);
            return Optional.of(updated);
        }
        log.warn("No se encontró host id={} para editar", idHost);
        return Optional.empty();
    }

    /**
     * Autentica a un administrador usando las credenciales provistas.
     *
     * <p><b>Privacidad:</b> no se registran credenciales en logs.</p>
     *
     * @param login credenciales de acceso (usuario/correo + contraseña)
     * @return {@link AdministratorDTO} autenticado si las credenciales son válidas; puede ser {@code null} si no
     * @throws RuntimeException si el DAO produce un error durante la autenticación
     * @implSpec Delegado directo a {@link AdministratorDAO#login(LoginDTO)}.
     */
    @Override
    public AdministratorDTO login(LoginDTO login) {
        log.debug("Intento de login de usuario (credenciales redacted)");

        AdministratorDTO user = dao.login(login);

        if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            log.info("Login fallido para usuario {}", login.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        AdministratorDTO userDTO = new AdministratorDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        String token = jwtConfig.generateToken(user.getEmail());
        userDTO.setToken(token);

        log.info("Login exitoso para usuario {}", user.getName());
        return userDTO; // <-- Devuelves el DTO correcto
    }
}
