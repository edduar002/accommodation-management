package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CityService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CityDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
