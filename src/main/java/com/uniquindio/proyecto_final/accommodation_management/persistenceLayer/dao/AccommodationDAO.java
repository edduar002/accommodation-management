package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AccommodationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link AccommodationDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link AccommodationRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link AccommodationMapper}.</p>
 *
 * <p>Incluye operaciones de guardado, consulta por disponibilidad, listado
 * por host y búsqueda por ID.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class AccommodationDAO {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;

    /**
     * Persiste un {@link AccommodationDTO} en la base de datos.
     *
     * @param dto DTO a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public AccommodationDTO save(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene todos los alojamientos disponibles.
     *
     * @return lista de {@link AccommodationDTO} disponibles
     */
    public List<AccommodationDTO> searchAvailableAccommodations() {
        List<AccommodationEntity> entities = accommodationRepository.searchAvailableAccommodations();
        return entities.stream()
                .map(accommodationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lista los alojamientos propiedad de un host específico.
     *
     * @param idHost identificador del host
     * @return lista de {@link AccommodationDTO} del host
     */
    public List<AccommodationDTO> ownAccommodationList(int idHost) {
        List<AccommodationEntity> entities = accommodationRepository.ownAccommodationList(idHost);
        return entities.stream()
                .map(accommodationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un alojamiento por su ID.
     *
     * @param id identificador del alojamiento
     * @return {@link Optional} con el DTO si existe; vacío si no
     */
    public Optional<AccommodationDTO> findById(int id) {
        return accommodationRepository.findById(id)
                .map(accommodationMapper::toDTO);
    }
}