package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.FavoriteService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteAccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.FavoriteEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.FavoriteMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteAccommodationRepository;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.FavoriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final FavoriteMapper favoriteMapper;

    @Autowired
    private final FavoriteRepository favoriteRepository;

    @Autowired
    private final AccommodationRepository accommodationRepository;

    @Autowired
    private final FavoriteAccommodationRepository favoriteAccommodationRepository;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para favoritos (no nulo)
     */
    public FavoriteServiceImpl(FavoriteDAO dao, FavoriteMapper favoriteMapper, FavoriteRepository favoriteRepository, AccommodationRepository accommodationRepository, FavoriteAccommodationRepository favoriteAccommodationRepository) {
        this.dao = dao;
        this.favoriteMapper = favoriteMapper;
        this.favoriteRepository = favoriteRepository;
        this.accommodationRepository = accommodationRepository;
        this.favoriteAccommodationRepository = favoriteAccommodationRepository;
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

    @Transactional
    public FavoriteDTO save(FavoriteDTO dto) {
        log.debug("Guardando favorito: {}", dto);

        // 1️⃣ Crear entidad FavoriteEntity
        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setActive(dto.isActive());
        favoriteEntity.setUserId(dto.getUserId());

        FavoriteEntity savedFavorite = favoriteRepository.save(favoriteEntity);

        // 2️⃣ Crear relación intermedia
        AccommodationEntity accommodation = accommodationRepository.findById(dto.getAccommodationId())
                .orElseThrow(() -> new RuntimeException("Alojamiento no encontrado con id: " + dto.getAccommodationId()));

        FavoriteAccommodationEntity relation = new FavoriteAccommodationEntity();
        relation.setFavorite(savedFavorite);
        relation.setAccommodation(accommodation);
        relation.setActive(true);

        favoriteAccommodationRepository.save(relation);

        // 3️⃣ Devolver DTO de favorito
        return favoriteMapper.toDTO(savedFavorite);
    }


}
