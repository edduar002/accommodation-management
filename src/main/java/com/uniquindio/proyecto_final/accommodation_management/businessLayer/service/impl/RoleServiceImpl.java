package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
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
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un rol: {@link #save(RoleDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see RoleDAO
 * @see RoleService
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para roles (no nulo)
     */
    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link RoleDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del rol a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link RoleDAO#save(RoleDTO)}.
     */
    @Override
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        log.debug("Guardando rol: {}", dto);
        RoleDTO saved = dao.save(dto);
        log.info("Rol guardado: {}", saved);
        return saved;
    }

    @Override
    public List<RoleDTO> rolesList() {
        log.debug("Buscando todos los roles");
        List<RoleDTO> list = dao.rolesList();
        log.info("Encontrados {} roles", list.size());
        return list;
    }

    @Override
    public RoleDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        RoleDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    @Transactional
    @Override
    public Optional<RoleDTO> edit(int id, RoleDTO user) {
        log.debug("Editando usuario id={} con newName={}", id, user.getName());
        Optional<RoleDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            RoleDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            RoleDTO updated = dao.save(userNew);
            log.info("Usuario id={} actualizado (name)", id);
            return Optional.of(updated);
        }
        log.warn("No se encontró usuario id={} para editar", id);
        return userDb;
    }

    @Transactional
    @Override
    public Optional<RoleDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<RoleDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            RoleDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            RoleDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }
}
