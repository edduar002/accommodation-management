package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.CityMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link CityDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link CityRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link CityMapper}.</p>
 *
 * <p>Incluye operaciones de guardado, listado y búsqueda por ID.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class CityDAO {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    /**
     * Persiste un {@link CityDTO} en la base de datos.
     *
     * @param dto DTO de la ciudad a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public CityDTO save(CityDTO dto) {
        CityEntity entity = cityMapper.toEntity(dto);
        CityEntity savedEntity = cityRepository.save(entity);
        return cityMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene la lista de todas las ciudades.
     *
     * @return lista de {@link CityDTO} presentes en la base de datos
     */
    public List<CityDTO> citiesList() {
        List<CityEntity> entities = cityRepository.allCities();
        return entities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de ciudades de un departamento específico.
     *
     * @param id identificador del departamento
     * @return lista de {@link CityDTO} asociadas al departamento
     */
    public List<CityDTO> citiesListDepartment(int id) {
        List<CityEntity> entities = cityRepository.citiesListDepartment(id);
        return entities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca una ciudad por su ID.
     *
     * @param id identificador de la ciudad
     * @return {@link Optional} con el DTO si existe; vacío si no
     */
    public Optional<CityDTO> findById(int id) {
        return cityRepository.findById(id)
                .map(cityMapper::toDTO);
    }
}