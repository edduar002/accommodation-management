package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para gestionar {@link FavoriteDTO}.
 *
 * <p>La clase delega la persistencia en {@link FavoriteDAO} y no introduce
 * reglas adicionales; su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un favorito: {@link #save(FavoriteDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see FavoriteDAO
 * @see FavoriteService
 */
@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para favoritos (no nulo)
     */
    public FavoriteServiceImpl(FavoriteDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link FavoriteDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del favorito a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link FavoriteDAO#save(FavoriteDTO)}.
     */
    @Override
    @Transactional
    public FavoriteDTO save(FavoriteDTO dto) {
        log.debug("Guardando favorito: {}", dto);
        FavoriteDTO saved = dao.save(dto);
        log.info("Favorito guardado: {}", saved);
        return saved;
    }
}
