package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CityService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CityDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar {@link CityDTO}.
 *
 * <p>La clase delega la persistencia en {@link CityDAO} y no introduce reglas adicionales;
 * su función es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar una ciudad: {@link #save(CityDTO)}.</li>
 * </ul>
 *
 * @author
 *   Equipo Prg Avanzada
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see CityDAO
 * @see CityService
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final CityDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     *
     * @param dao componente de acceso a datos para ciudades (no nulo)
     */
    public CityServiceImpl(CityDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste una ciudad.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO de la ciudad a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link CityDAO#save(CityDTO)}.
     */
    @Override
    @Transactional
    public CityDTO save(CityDTO dto) {
        log.debug("Guardando ciudad: {}", dto);
        CityDTO saved = dao.save(dto);
        log.info("Ciudad guardada: {}", saved);
        return saved;
    }

    @Override
    public List<CityDTO> citiesList() {
        log.debug("Buscando todas las ciudades");
        List<CityDTO> list = dao.citiesList();
        log.info("Encontrados {} ciudades", list.size());
        return list;
    }

    @Transactional
    @Override
    public Optional<CityDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<CityDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            CityDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            CityDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }

    @Override
    public CityDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        CityDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    @Transactional
    @Override
    public Optional<CityDTO> edit(int id, CityDTO user) {
        log.debug("Editando usuario id={} con newName={}", id, user.getName());
        Optional<CityDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            CityDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            CityDTO updated = dao.save(userNew);
            log.info("Usuario id={} actualizado (name)", id);
            return Optional.of(updated);
        }
        log.warn("No se encontró usuario id={} para editar", id);
        return userDb;
    }

}
