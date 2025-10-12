package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteAccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteAccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteAccommodationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para gestionar {@link FavoriteAccommodationDTO}.
 *
 * <p>La clase delega la persistencia en {@link FavoriteAccommodationDAO} y no introduce
 * reglas adicionales; su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un favorito: {@link #save(FavoriteAccommodationDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see FavoriteAccommodationDAO
 * @see FavoriteAccommodationService
 */
@Slf4j
@Service
public class FavoriteAccommodationServiceImpl implements FavoriteAccommodationService {

    private final FavoriteAccommodationDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para favoritos (no nulo)
     */
    public FavoriteAccommodationServiceImpl(FavoriteAccommodationDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link FavoriteAccommodationDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del favorito a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link FavoriteAccommodationDAO#save(FavoriteAccommodationDTO)}.
     */
    @Override
    @Transactional
    public FavoriteAccommodationDTO save(FavoriteAccommodationDTO dto) {
        log.debug("Guardando favorito de alojamiento: {}", dto);
        FavoriteAccommodationDTO saved = dao.save(dto);
        log.info("Favorito de alojamiento guardado: {}", saved);
        return saved;
    }
}
