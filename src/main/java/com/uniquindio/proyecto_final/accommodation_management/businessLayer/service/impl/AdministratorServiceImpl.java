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
 * Servicio de negocio para gestionar administradores.
 * Orquesta operaciones y delega persistencia a {@link AdministratorDAO}.
 */
@Slf4j
@Service
public class AdministratorServiceImpl implements AdministratorService {

    // DAO de acceso a administradores en BD
    private final AdministratorDAO administratorDAO;

    // Codificador de contraseñas
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Configuración para generación de JWT
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * Constructor con inyección de DAO.
     */
    public AdministratorServiceImpl(AdministratorDAO administratorDAO) {
        // Guardar DAO recibido
        this.administratorDAO = administratorDAO;
    }

    /**
     * Guarda un administrador en la BD.
     */
    @Override
    @Transactional
    public AdministratorDTO save(AdministratorDTO adminDTO) {
        // Validar fortaleza de contraseña
        PasswordValidator.validate(adminDTO.getPassword());
        // Encriptar contraseña antes de guardar
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        // Registrar acción
        log.debug("Guardando administrador: {}", adminDTO);
        // Persistir en la BD
        AdministratorDTO savedAdmin = administratorDAO.save(adminDTO);
        // Confirmar guardado
        log.info("Administrador guardado: {}", savedAdmin);
        // Retornar resultado
        return savedAdmin;
    }

    /**
     * Cambia contraseña de un administrador.
     */
    @Transactional
    @Override
    public Optional<AdministratorDTO> changePassword(int id, ChangePasswordDTO changePasswordDTO) {
        // Registrar intento sin mostrar credenciales
        log.debug("changePassword userId={} (credenciales ocultas)", id);
        // Buscar el administrador en BD
        Optional<AdministratorDTO> adminDb = administratorDAO.findById(id);

        // Si existe
        if (adminDb.isPresent()) {
            // Obtener registro
            AdministratorDTO admin = adminDb.get();
            // Validar contraseña actual mediante coincidencia encriptada
            if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), admin.getPassword())) {
                // Encriptar nueva contraseña
                admin.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                // Guardar cambios
                administratorDAO.save(admin);
                // Registrar éxito
                log.info("Contraseña actualizada para userId={}", id);
                // Retornar admin actualizado
                return Optional.of(admin);
            }
            // Si contraseña antigua no coincide
            log.warn("Contraseña antigua incorrecta para userId={}", id);
            return Optional.empty();
        }

        // Si admin no existe
        log.warn("No se encontró userId={} para cambiar contraseña", id);
        return Optional.empty();
    }

    /**
     * Obtiene detalle de un administrador por ID.
     */
    @Override
    public AdministratorDTO detail(int adminId) {
        // Registrar consulta
        log.debug("Consultando detalle de administrador id={}", adminId);
        // Buscar por ID
        AdministratorDTO admin = administratorDAO.findById(adminId).orElse(null);
        // Registrar resultado
        log.info("Detalle id={} {}", adminId, admin != null ? "encontrado" : "no encontrado");
        // Retornar resultado
        return admin;
    }

    /**
     * Edita información básica de un administrador.
     */
    @Override
    @Transactional
    public Optional<AdministratorDTO> edit(int adminId, AdministratorDTO newAdminData) {
        // Registrar acción
        log.debug("Editando admin id={} con newName={}", adminId, newAdminData.getName());
        // Buscar admin existente
        Optional<AdministratorDTO> adminDb = administratorDAO.findById(adminId);

        // Si existe, actualizar
        if (adminDb.isPresent()) {
            // Obtener entidad existente
            AdministratorDTO admin = adminDb.get();
            // Actualizar campos editables
            admin.setName(newAdminData.getName());
            admin.setSurname(newAdminData.getSurname());
            admin.setEmail(newAdminData.getEmail());
            // Guardar cambios
            AdministratorDTO updatedAdmin = administratorDAO.save(admin);
            // Log de éxito
            log.info("Administrador id={} actualizado", adminId);
            // Retornar resultado
            return Optional.of(updatedAdmin);
        }

        // Si no existe
        log.warn("No se encontró admin id={} para editar", adminId);
        return Optional.empty();
    }

    /**
     * Inicia sesión validando credenciales y generando token JWT.
     */
    @Override
    public AdministratorDTO login(LoginDTO loginDTO) {
        // Registrar intento sin exponer credenciales
        log.debug("Intento de login (credenciales ocultas)");

        // Buscar usuario por login
        AdministratorDTO admin = administratorDAO.login(loginDTO);

        // Validar credenciales
        if (admin == null || !passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())) {
            log.info("Login fallido para usuario {}", loginDTO.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        // Crear objeto de respuesta sin contraseña
        AdministratorDTO adminResponse = new AdministratorDTO();
        adminResponse.setId(admin.getId());
        adminResponse.setName(admin.getName());
        adminResponse.setEmail(admin.getEmail());

        // Generar token JWT
        String token = jwtConfig.generateToken(admin.getEmail());
        adminResponse.setToken(token);

        // Log de éxito
        log.info("Login exitoso para usuario {}", admin.getName());
        // Retornar DTO con token
        return adminResponse;
    }
}