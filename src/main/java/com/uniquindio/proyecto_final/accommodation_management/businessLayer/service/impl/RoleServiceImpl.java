package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.RoleService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.RoleDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link RoleDTO}.
 *
 * <p>La clase delega la persistencia en {@link RoleDAO} y no introduce reglas adicionales;
 * su función es orquestar la llamada al DAO.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    // DAO encargado del acceso a datos para roles
    private final RoleDAO roleDAO;

    /**
     * Constructor que inicializa la dependencia DAO.
     * @param roleDAO componente de acceso a datos (no nulo)
     */
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    /**
     * Guarda un rol en el sistema.
     *
     * <p>Operación transaccional para garantizar consistencia.</p>
     *
     * @param roleDTO DTO con la información del rol a guardar
     * @return Rol persistido
     */
    @Override
    @Transactional
    public RoleDTO save(RoleDTO roleDTO) {
        log.debug("Guardando rol: {}", roleDTO);
        RoleDTO savedRole = roleDAO.save(roleDTO);
        log.info("Rol guardado: {}", savedRole);
        return savedRole;
    }

    /**
     * Obtiene la lista completa de roles.
     *
     * @return Lista de roles disponibles
     */
    @Override
    public List<RoleDTO> rolesList() {
        log.debug("Listando todos los roles");
        List<RoleDTO> roles = roleDAO.rolesList();
        log.info("Total roles encontrados: {}", roles.size());
        return roles;
    }

    /**
     * Obtiene el detalle de un rol por su ID.
     *
     * @param roleId ID del rol
     * @return Rol encontrado o null si no existe
     */
    @Override
    public RoleDTO detail(int roleId) {
        log.debug("Buscando rol con id={}", roleId);
        RoleDTO foundRole = roleDAO.findById(roleId).orElse(null);
        log.info("Rol id={} {}", roleId, foundRole != null ? "encontrado" : "no encontrado");
        return foundRole;
    }

    /**
     * Edita el nombre de un rol.
     *
     * @param id ID del rol a modificar
     * @param roleData DTO con los nuevos datos
     * @return Rol actualizado o vacío si no existe
     */
    @Transactional
    @Override
    public Optional<RoleDTO> edit(int id, RoleDTO roleData) {
        log.debug("Editando rol id={} nuevoNombre={}", id, roleData.getName());
        Optional<RoleDTO> roleDb = roleDAO.findById(id);

        if (roleDb.isPresent()) {
            RoleDTO existingRole = roleDb.get();
            existingRole.setName(roleData.getName());
            RoleDTO updatedRole = roleDAO.save(existingRole);
            log.info("Rol id={} actualizado correctamente", id);
            return Optional.of(updatedRole);
        }

        log.warn("No se encontró rol id={} para actualización", id);
        return roleDb;
    }

    /**
     * Inactiva un rol (soft delete).
     *
     * @param id ID del rol a inactivar
     * @return Rol inactivado o vacío si no existe
     */
    @Transactional
    @Override
    public Optional<RoleDTO> delete(int id) {
        log.debug("Inactivando rol id={}", id);
        Optional<RoleDTO> roleDb = roleDAO.findById(id);

        if (roleDb.isPresent()) {
            RoleDTO role = roleDb.get();
            role.setActive(false);
            RoleDTO savedRole = roleDAO.save(role);
            log.info("Rol id={} inactivado correctamente", id);
            return Optional.of(savedRole);
        }

        log.warn("No se encontró rol id={} para inactivar", id);
        return roleDb;
    }
}