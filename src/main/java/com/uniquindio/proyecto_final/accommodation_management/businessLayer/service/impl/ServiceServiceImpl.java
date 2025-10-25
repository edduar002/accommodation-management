package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ServiceDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link ServiceDTO}.
 *
 * <p>Esta clase delega la persistencia en {@link ServiceDAO} y no introduce
 * reglas adicionales; su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un servicio: {@link #save(ServiceDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see ServiceDAO
 * @see ServiceService
 */
@Slf4j
@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para servicios (no nulo)
     */
    public ServiceServiceImpl(ServiceDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link ServiceDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del servicio a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link ServiceDAO#save(ServiceDTO)}.
     */
    @Override
    @Transactional
    public ServiceDTO save(ServiceDTO dto) {
        log.debug("Guardando servicio: {}", dto);
        ServiceDTO saved = dao.save(dto);
        log.info("Servicio guardado: {}", saved);
        return saved;
    }

    @Override
    public List<ServiceDTO> servicesList() {
        log.debug("Buscando todos los roles");
        List<ServiceDTO> list = dao.servicesList();
        log.info("Encontrados {} servicios", list.size());
        return list;
    }

    @Override
    public ServiceDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        ServiceDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    @Transactional
    @Override
    public Optional<ServiceDTO> edit(int id, ServiceDTO user) {
        log.debug("Editando usuario id={} con newName={}", id, user.getName());
        Optional<ServiceDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            ServiceDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            ServiceDTO updated = dao.save(userNew);
            log.info("Usuario id={} actualizado (name)", id);
            return Optional.of(updated);
        }
        log.warn("No se encontró usuario id={} para editar", id);
        return userDb;
    }

    @Transactional
    @Override
    public Optional<ServiceDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<ServiceDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            ServiceDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            ServiceDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }
}
