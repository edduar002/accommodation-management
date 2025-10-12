package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.RoleService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.RoleDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
