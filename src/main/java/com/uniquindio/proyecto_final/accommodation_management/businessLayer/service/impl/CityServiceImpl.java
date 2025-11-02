package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CityService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CityDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para gestión de ciudades.
 * Delegado de persistencia: {@link CityDAO}.
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    // DAO para acceso a datos de ciudades
    private final CityDAO cityDAO;

    /**
     * Constructor con inyección de DAO.
     * @param cityDAO DAO de ciudades
     */
    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    /**
     * Guarda una ciudad en la base de datos.
     */
    @Override
    @Transactional
    public CityDTO save(CityDTO cityDTO) {
        // Registrar acción
        log.debug("Guardando ciudad: {}", cityDTO);
        // Guardar en base de datos
        CityDTO savedCity = cityDAO.save(cityDTO);
        // Confirmar guardado
        log.info("Ciudad guardada: {}", savedCity);
        // Retornar resultado
        return savedCity;
    }

    /**
     * Obtiene todas las ciudades activas.
     */
    @Override
    public List<CityDTO> citiesList() {
        // Registrar búsqueda
        log.debug("Buscando todas las ciudades");
        // Consultar lista completa
        List<CityDTO> cities = cityDAO.citiesList();
        // Registrar cantidad
        log.info("Se encontraron {} ciudades", cities.size());
        // Retornar lista
        return cities;
    }

    /**
     * Obtiene todas las ciudades pertenecientes a un departamento.
     */
    @Override
    public List<CityDTO> citiesListDepartment(int departmentId) {
        // Registrar búsqueda
        log.debug("Buscando ciudades del departamento id={}", departmentId);
        // Consultar ciudades del departamento
        List<CityDTO> cities = cityDAO.citiesListDepartment(departmentId);
        // Registrar cantidad
        log.info("Se encontraron {} ciudades en el departamento id={}", cities.size(), departmentId);
        // Retornar lista
        return cities;
    }

    /**
     * Inactiva una ciudad (soft delete).
     */
    @Transactional
    @Override
    public Optional<CityDTO> delete(int cityId) {
        // Registrar acción
        log.debug("Inactivando ciudad id={}", cityId);
        // Buscar ciudad
        Optional<CityDTO> cityDb = cityDAO.findById(cityId);

        // Si existe, modificar estado
        if (cityDb.isPresent()) {
            CityDTO city = cityDb.get();
            // Desactivar ciudad
            city.setActive(false);
            // Guardar actualización
            CityDTO updatedCity = cityDAO.save(city);
            // Registrar éxito
            log.info("Ciudad id={} inactivada", cityId);
            // Retornar resultado
            return Optional.of(updatedCity);
        }

        // Si no existe
        log.warn("No se encontró ciudad id={} para inactivar", cityId);
        return cityDb;
    }

    /**
     * Obtiene detalle de una ciudad.
     */
    @Override
    public CityDTO detail(int cityId) {
        // Registrar consulta
        log.debug("Consultando detalle de ciudad id={}", cityId);
        // Buscar por ID
        CityDTO city = cityDAO.findById(cityId).orElse(null);
        // Registrar resultado
        log.info("Detalle ciudad id={} {}", cityId, city != null ? "encontrado" : "no encontrado");
        // Retornar resultado
        return city;
    }

    /**
     * Edita nombre y departamento de una ciudad.
     */
    @Transactional
    @Override
    public Optional<CityDTO> edit(int cityId, CityDTO newCityData) {
        // Registrar acción
        log.debug("Editando ciudad id={} con newName={}", cityId, newCityData.getName());
        // Buscar ciudad existente
        Optional<CityDTO> cityDb = cityDAO.findById(cityId);

        // Si existe, actualizar
        if (cityDb.isPresent()) {
            CityDTO city = cityDb.get();
            // Actualizar valores modificables
            city.setName(newCityData.getName());
            city.setDepartmentsId(newCityData.getDepartmentsId());
            // Guardar cambios
            CityDTO updatedCity = cityDAO.save(city);
            // Registrar éxito
            log.info("Ciudad id={} actualizada", cityId);
            // Retornar actualizada
            return Optional.of(updatedCity);
        }

        // Si no existe
        log.warn("No se encontró ciudad id={} para editar", cityId);
        return cityDb;
    }
}