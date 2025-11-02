package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.validators.PasswordValidator;
import com.uniquindio.proyecto_final.accommodation_management.config.JwtConfig;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link UserDTO}.
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    // DAO encargado de la persistencia de usuarios
    private final UserDAO userDAO;

    // Codificador seguro de contraseñas
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Configuración del sistema de tokens JWT
    @Autowired
    private JwtConfig jwtConfig;

    // Constructor con inyección del DAO
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Guarda un usuario en la BD
    @Override
    public UserDTO save(UserDTO dto) {

        // Validar la contraseña antes de encriptarla
        PasswordValidator.validate(dto.getPassword());

        // Encriptar contraseña
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        log.debug("Guardando usuario (sin exponer contraseña): {}", dto.getEmail());
        UserDTO saved = userDAO.save(dto);
        log.info("Usuario guardado con id={}", saved.getId());
        return saved;
    }

    // Login y generación de token
    @Override
    public UserDTO login(LoginDTO login) {
        log.debug("Intento de login usuario={}", login.getEmail());

        UserDTO user = userDAO.login(login);

        if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            log.warn("Login fallido usuario={}", login.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        // Crear DTO sin exponer la contraseña
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setActive(user.isActive());
        userDTO.setImgUrl(user.getImgUrl());

        // Generar token
        String token = jwtConfig.generateToken(user.getEmail());
        userDTO.setToken(token);

        log.info("Login exitoso usuario={}", user.getEmail());
        return userDTO;
    }

    // Obtiene detalle por ID
    @Override
    public UserDTO detail(int userId) {
        log.debug("Consultando detalle usuario id={}", userId);
        UserDTO dto = userDAO.findById(userId).orElse(null);
        log.info("Detalle usuario id={} {}", userId, dto != null ? "encontrado" : "no encontrado");
        return dto;
    }

    // Editar usuario
    @Transactional
    @Override
    public Optional<UserDTO> edit(int id, UserDTO newData) {
        log.debug("Editando usuario id={}", id);
        Optional<UserDTO> userDb = userDAO.findById(id);

        if (userDb.isPresent()) {
            UserDTO user = userDb.get();
            user.setName(newData.getName());
            user.setSurname(newData.getSurname());
            user.setEmail(newData.getEmail());
            user.setPhone(newData.getPhone());
            user.setDepartmentId(newData.getDepartmentId());
            user.setCitiesId(newData.getCitiesId());
            user.setImgUrl(newData.getImgUrl());

            UserDTO updated = userDAO.save(user);
            log.info("Usuario id={} actualizado correctamente", id);
            return Optional.of(updated);
        }

        log.warn("Usuario id={} no encontrado para editar", id);
        return Optional.empty();
    }

    // Cambiar contraseña del usuario
    @Transactional
    @Override
    public Optional<UserDTO> changePassword(int id, ChangePasswordDTO changePassDTO) {
        log.debug("Solicitando cambio de contraseña userId={}", id);
        Optional<UserDTO> userDb = userDAO.findById(id);

        if (userDb.isPresent()) {
            UserDTO user = userDb.get();

            // Validar coincidencia con la contraseña guardada
            if (!passwordEncoder.matches(changePassDTO.getOldPassword(), user.getPassword())) {
                log.warn("Contraseña antigua incorrecta userId={}", id);
                return Optional.empty();
            }

            PasswordValidator.validate(changePassDTO.getNewPassword());
            user.setPassword(passwordEncoder.encode(changePassDTO.getNewPassword()));

            userDAO.save(user);
            log.info("Contraseña actualizada userId={}", id);
            return Optional.of(user);
        }

        log.warn("Usuario no encontrado userId={}", id);
        return Optional.empty();
    }

    // Recuperación de contraseña
    @Transactional
    @Override
    public Optional<UserDTO> recoveryPassword(int id, String newPassword) {
        log.debug("Recuperando contraseña userId={}", id);
        Optional<UserDTO> userDb = userDAO.findById(id);

        if (userDb.isPresent()) {
            PasswordValidator.validate(newPassword);
            UserDTO user = userDb.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userDAO.save(user);
            log.info("Contraseña restablecida userId={}", id);
            return Optional.of(user);
        }

        log.warn("No se encontró usuario para recuperar contraseña userId={}", id);
        return Optional.empty();
    }

    // Listar todos los usuarios
    @Override
    public List<UserDTO> usersList() {
        log.debug("Listando todos los usuarios");
        List<UserDTO> list = userDAO.usersList();
        log.info("Total usuarios encontrados={}", list.size());
        return list;
    }

    // Soft delete del usuario
    @Transactional
    @Override
    public Optional<UserDTO> delete(int id) {
        log.debug("Desactivando usuario id={}", id);
        Optional<UserDTO> userDb = userDAO.findById(id);

        if (userDb.isPresent()) {
            UserDTO user = userDb.get();
            user.setActive(false);
            UserDTO saved = userDAO.save(user);
            log.info("Usuario id={} desactivado correctamente", id);
            return Optional.of(saved);
        }

        log.warn("Usuario id={} no encontrado para desactivar", id);
        return Optional.empty();
    }
}