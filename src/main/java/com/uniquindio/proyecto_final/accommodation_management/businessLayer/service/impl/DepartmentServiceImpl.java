package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.DepartmentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.DepartmentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link DepartmentDTO}.
 *
 * <p>La clase delega la persistencia en {@link DepartmentDAO} y no introduce reglas adicionales;
 * su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un departamento: {@link #save(DepartmentDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see DepartmentDAO
 * @see DepartmentService
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para departamentos (no nulo)
     */
    public DepartmentServiceImpl(DepartmentDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link DepartmentDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del departamento a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link DepartmentDAO#save(DepartmentDTO)}.
     */
    @Override
    @Transactional
    public DepartmentDTO save(DepartmentDTO dto) {
        log.debug("Guardando departamento: {}", dto);
        DepartmentDTO saved = dao.save(dto);
        log.info("Departamento guardado: {}", saved);
        return saved;
    }

    @Override
    public List<DepartmentDTO> departmentsList() {
        log.debug("Buscando todos los departamentos");
        List<DepartmentDTO> list = dao.departmentsList();
        log.info("Encontrados {} departamentos", list.size());
        return list;
    }

    @Override
    public DepartmentDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        DepartmentDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    @Transactional
    @Override
    public Optional<DepartmentDTO> edit(int id, DepartmentDTO user) {
        log.debug("Editando usuario id={} con newName={}", id, user.getName());
        Optional<DepartmentDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            DepartmentDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            DepartmentDTO updated = dao.save(userNew);
            log.info("Usuario id={} actualizado (name)", id);
            return Optional.of(updated);
        }
        log.warn("No se encontró usuario id={} para editar", id);
        return userDb;
    }
}
