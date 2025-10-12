package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationServiceService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationServiceDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para administrar los servicios asociados a un alojamiento
 * (por ejemplo: wifi, parqueadero, etc.), representados por {@link AccommodationServiceDTO}.
 *
 * <p>La lógica de negocio es mínima y se delega en {@link AccommodationServiceDAO}.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear/guardar un servicio de alojamiento: {@link #save(AccommodationServiceDTO)}.</li>
 * </ul>
 *
 * @author
 *   Equipo Prg Avanzada
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see AccommodationServiceDAO
 * @see AccommodationServiceService
 */
@Slf4j
@Service
public class AccommodationServiceServiceImpl implements AccommodationServiceService {

    private final AccommodationServiceDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     *
     * @param dao componente de acceso a datos para {@link AccommodationServiceDTO} (no nulo)
     */
    public AccommodationServiceServiceImpl(AccommodationServiceDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link AccommodationServiceDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO de entrada (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link AccommodationServiceDAO#save(AccommodationServiceDTO)}.
     */
    @Override
    @Transactional
    public AccommodationServiceDTO save(AccommodationServiceDTO dto) {
        // Logs (no alteran la lógica):
        log.debug("Guardando servicio de alojamiento: {}", dto);
        AccommodationServiceDTO saved = dao.save(dto);
        log.info("Servicio de alojamiento guardado: {}", saved);
        return saved;
    }
}
