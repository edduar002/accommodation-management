package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.validators.PasswordValidator;
import com.uniquindio.proyecto_final.accommodation_management.config.JwtConfig;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.HostDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para gestionar operaciones sobre anfitriones (Hosts).
 *
 * <p>Aplica reglas simples relacionadas con actualización de datos y manejo de contraseñas,
 * delegando la persistencia en {@link HostDAO}.</p>
 */
@Slf4j
@Service
public class HostServiceImpl implements HostService {

    // DAO responsable de persistencia de anfitriones
    private final HostDAO hostDAO;

    // Codificador para contraseñas
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Configuración para emisión de JWT
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * Constructor con inyección del DAO.
     */
    public HostServiceImpl(HostDAO hostDAO) {
        this.hostDAO = hostDAO;
    }

    /**
     * Guarda un nuevo anfitrión después de validar y encriptar contraseña.
     */
    @Override
    @Transactional
    public HostDTO save(HostDTO hostDTO) {

        // Validación de política de contraseña
        PasswordValidator.validate(hostDTO.getPassword());

        // Encriptación
        hostDTO.setPassword(passwordEncoder.encode(hostDTO.getPassword()));

        // Registro seguro
        log.debug("Guardando host (datos sensibles ocultos)");

        // Persistencia
        HostDTO savedHost = hostDAO.save(hostDTO);

        // Confirmación
        log.info("Host guardado correctamente");
        return savedHost;
    }

    /**
     * Obtiene el detalle de un host por su ID.
     */
    @Override
    public HostDTO detail(int hostId) {

        // Consulta en DAO
        log.debug("Consultando detalle de host id={}", hostId);
        HostDTO hostDTO = hostDAO.findById(hostId).orElse(null);

        // Registro del resultado
        log.info("Detalle host id={} {}", hostId, (hostDTO != null ? "encontrado" : "no encontrado"));
        return hostDTO;
    }

    /**
     * Edita los datos básicos del anfitrión.
     */
    @Override
    @Transactional
    public Optional<HostDTO> edit(int hostId, HostDTO hostData) {

        log.debug("Editando host id={} (nuevos datos básicos)", hostId);

        Optional<HostDTO> hostDb = hostDAO.findById(hostId);

        if (hostDb.isPresent()) {

            // Host encontrado
            HostDTO hostToUpdate = hostDb.get();

            // Se actualizan campos permitidos
            hostToUpdate.setDepartmentsId(hostData.getDepartmentsId());
            hostToUpdate.setCitiesId(hostData.getCitiesId());
            hostToUpdate.setName(hostData.getName());
            hostToUpdate.setSurname(hostData.getSurname());
            hostToUpdate.setEmail(hostData.getEmail());
            hostToUpdate.setImgUrl(hostData.getImgUrl());
            hostToUpdate.setPhone(hostData.getPhone());
            hostToUpdate.setPersonalDescription(hostData.getPersonalDescription());

            // Guardar
            HostDTO updatedHost = hostDAO.save(hostToUpdate);

            // Confirmación
            log.info("Host id={} actualizado correctamente", hostId);
            return Optional.of(updatedHost);
        }

        log.warn("No se encontró host id={} para editar", hostId);
        return Optional.empty();
    }

    /**
     * Cambia la contraseña de un host verificando primero la contraseña anterior.
     */
    @Transactional
    @Override
    public Optional<HostDTO> changePassword(int hostId, ChangePasswordDTO passwordData) {

        log.debug("changePassword hostId={} (contraseñas ocultas)", hostId);

        Optional<HostDTO> hostDb = hostDAO.findById(hostId);

        if (hostDb.isPresent()) {

            HostDTO host = hostDb.get();

            // Comparación de contraseña anterior
            if (passwordEncoder.matches(passwordData.getOldPassword(), host.getPassword())) {

                // Validar la nueva contraseña
                PasswordValidator.validate(passwordData.getNewPassword());

                // Encriptar y guardar
                host.setPassword(passwordEncoder.encode(passwordData.getNewPassword()));
                hostDAO.save(host);

                log.info("Contraseña actualizada correctamente para hostId={}", hostId);
                return Optional.of(host);
            }

            log.warn("Contraseña anterior incorrecta para hostId={}", hostId);
            return Optional.empty();
        }

        log.warn("No se encontró host id={} para changePassword", hostId);
        return Optional.empty();
    }

    /**
     * Autentica al host y genera JWT si la contraseña es correcta.
     */
    @Override
    public HostDTO login(LoginDTO loginDTO) {

        log.debug("Intento de login (credenciales protegidas)");

        HostDTO host = hostDAO.login(loginDTO);

        if (host == null || !passwordEncoder.matches(loginDTO.getPassword(), host.getPassword())) {
            log.info("Login fallido para {}", loginDTO.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        // Preparar DTO seguro para la respuesta
        HostDTO responseDTO = new HostDTO();
        responseDTO.setId(host.getId());
        responseDTO.setName(host.getName());
        responseDTO.setSurname(host.getSurname());
        responseDTO.setEmail(host.getEmail());
        responseDTO.setPhone(host.getPhone());
        responseDTO.setActive(host.isActive());
        responseDTO.setImgUrl(host.getImgUrl());

        // Generar token
        responseDTO.setToken(jwtConfig.generateToken(host.getEmail()));

        log.info("Login exitoso para host {}", host.getName());
        return responseDTO;
    }

    /**
     * Establece una nueva contraseña para el host (recuperación).
     */
    @Transactional
    @Override
    public Optional<HostDTO> recoveryPassword(int hostId, String newPassword) {

        log.debug("recoveryPassword hostId={} (contraseña oculta)", hostId);

        Optional<HostDTO> hostDb = hostDAO.findById(hostId);

        if (hostDb.isPresent()) {

            HostDTO host = hostDb.get();
            host.setPassword(passwordEncoder.encode(newPassword));
            hostDAO.save(host);

            log.info("Contraseña recuperada para hostId={}", hostId);
            return Optional.of(host);
        }

        log.warn("No se encontró host id={} para recoveryPassword", hostId);
        return Optional.empty();
    }

    /**
     * Marca un host como inactivo (soft delete).
     */
    @Transactional
    @Override
    public Optional<HostDTO> delete(int hostId) {

        log.debug("Inactivando host id={}", hostId);

        Optional<HostDTO> hostDb = hostDAO.findById(hostId);

        if (hostDb.isPresent()) {

            HostDTO host = hostDb.get();
            host.setActive(false);
            HostDTO saved = hostDAO.save(host);

            log.info("Host id={} inactivado correctamente", hostId);
            return Optional.of(saved);
        }

        log.warn("No se encontró host id={} para inactivar", hostId);
        return Optional.empty();
    }

    /**
     * Obtiene todos los anfitriones.
     */
    @Override
    public List<HostDTO> hostsList() {

        log.debug("Consultando lista completa de hosts");

        List<HostDTO> hosts = hostDAO.hostsList();

        log.info("Encontrados {} hosts", hosts.size());
        return hosts;
    }
}