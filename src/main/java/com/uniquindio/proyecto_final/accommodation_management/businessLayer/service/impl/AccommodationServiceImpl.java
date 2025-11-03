package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para operaciones sobre alojamientos.
 * Delegado de persistencia: {@link AccommodationDAO}.
 */
@Slf4j
@Service
public class AccommodationServiceImpl implements AccommodationService {

    // DAO para acceso a datos
    private final AccommodationDAO accommodationDAO;

    /**
     * Constructor inyectando dependencia DAO.
     * @param accommodationDAO acceso a datos
     */
    public AccommodationServiceImpl(AccommodationDAO accommodationDAO) {
        // Asignar DAO recibido
        this.accommodationDAO = accommodationDAO;
    }

    @Override
    public Double getAverageCalification(int accommodationId) {
        log.debug("Obteniendo promedio de calificaciones para alojamiento id={}", accommodationId);

        // Invocar DAO para calcular promedio
        Double average = accommodationDAO.getAverageCalification(accommodationId);

        // Retornar 0.0 si no hay calificaciones
        if (average == null) {
            log.info("No hay calificaciones para alojamiento id={}", accommodationId);
            return 0.0;
        }

        log.info("Promedio de calificaciones para alojamiento id={} = {}", accommodationId, average);
        return average;
    }


    /**
     * Guarda un nuevo alojamiento.
     * @param accommodationDTO datos del alojamiento a guardar
     * @return alojamiento guardado
     */
    @Override
    @Transactional
    public AccommodationDTO save(AccommodationDTO accommodationDTO) {
        // Registrar acción en debug
        log.debug("Guardando alojamiento: {}", accommodationDTO);
        // Guardar alojamiento en base de datos
        AccommodationDTO savedAccommodation = accommodationDAO.save(accommodationDTO);
        // Confirmar guardado en logs
        log.info("Alojamiento guardado: {}", savedAccommodation);
        // Retornar alojamiento guardado
        return savedAccommodation;
    }

    /**
     * Obtiene solo alojamientos activos/disponibles.
     * @return lista de alojamientos disponibles
     */
    @Override
    public List<AccommodationDTO> searchAvailableAccommodations() {
        // Registrar acción
        log.debug("Buscando alojamientos disponibles");
        // Consultar alojamiento filtrando estado activo
        List<AccommodationDTO> accommodations = accommodationDAO.searchAvailableAccommodations();
        // Reportar cantidad encontrada
        log.info("Encontrados {} alojamientos disponibles", accommodations.size());
        // Retornar lista
        return accommodations;
    }

    /**
     * Lista alojamientos pertenecientes a un anfitrión.
     * @param hostId identificador del anfitrión
     * @return lista de alojamientos
     */
    @Override
    public List<AccommodationDTO> ownAccommodationList(int hostId) {
        // Registrar acción
        log.debug("Listando alojamientos del hostId={}", hostId);
        // Consultar alojamientos del anfitrión
        List<AccommodationDTO> accommodations = accommodationDAO.ownAccommodationList(hostId);
        // Registrar tamaño
        log.info("Host {} tiene {} alojamientos", hostId, accommodations.size());
        // Retornar lista
        return accommodations;
    }

    /**
     * Actualiza campos editables de un alojamiento.
     * @param id id del alojamiento
     * @param newAccommodationData datos nuevos
     * @return Optional con alojamiento editado o vacío si no existe
     */
    @Transactional
    @Override
    public Optional<AccommodationDTO> edit(int id, AccommodationDTO newAccommodationData) {
        // Registrar acción
        log.debug("Editando alojamiento id={} con precio={}", id, newAccommodationData.getPrice());
        // Buscar alojamiento existente
        Optional<AccommodationDTO> existingAccommodation = accommodationDAO.findById(id);

        // Verificar existencia
        if (existingAccommodation.isPresent()) {
            // Obtener entidad existente
            AccommodationDTO accommodation = existingAccommodation.get();
            // Actualizar campos editables
            accommodation.setPrice(newAccommodationData.getPrice());
            accommodation.setDepartmentsId(newAccommodationData.getDepartmentsId());
            accommodation.setCitiesId(newAccommodationData.getCitiesId());
            accommodation.setDetailedDescription(newAccommodationData.getDetailedDescription());
            accommodation.setExactLocation(newAccommodationData.getExactLocation());
            accommodation.setMaximumCapacity(newAccommodationData.getMaximumCapacity());
            accommodation.setServices(newAccommodationData.getServices());
            accommodation.setImgUrl(newAccommodationData.getImgUrl());
            // Guardar cambios
            AccommodationDTO updatedAccommodation = accommodationDAO.save(accommodation);
            // Registrar éxito
            log.info("Alojamiento id={} actualizado", id);
            // Retornar actualizado
            return Optional.of(updatedAccommodation);
        }

        // Si no se encontró
        log.warn("No se encontró alojamiento id={} para editar", id);
        return existingAccommodation;
    }

    /**
     * Inactiva un alojamiento (soft delete).
     * @param id identificador
     * @return Optional con entidad inactiva o vacío si no existe
     */
    @Transactional
    @Override
    public Optional<AccommodationDTO> delete(int id) {
        // Registrar acción
        log.debug("Inactivando alojamiento id={}", id);
        // Buscar alojamiento existente
        Optional<AccommodationDTO> existingAccommodation = accommodationDAO.findById(id);

        // Verificar existencia
        if (existingAccommodation.isPresent()) {
            // Obtener alojamiento
            AccommodationDTO accommodation = existingAccommodation.get();
            // Cambiar estado activo a falso
            accommodation.setActive(false);
            // Guardar actualización
            AccommodationDTO savedAccommodation = accommodationDAO.save(accommodation);
            // Registrar éxito
            log.info("Alojamiento id={} inactivado", id);
            // Retornar resultado
            return Optional.of(savedAccommodation);
        }

        // Si no existe
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return existingAccommodation;
    }

    /**
     * Consulta detalle de un alojamiento.
     * @param accommodationId id del alojamiento
     * @return dto si existe, null si no
     */
    @Override
    public AccommodationDTO detail(int accommodationId) {
        // Registrar acción
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        // Buscar alojamiento por id
        AccommodationDTO accommodation = accommodationDAO.findById(accommodationId).orElse(null);
        // Registrar resultado
        log.info("Detalle id={} {}", accommodationId, accommodation != null ? "encontrado" : "no encontrado");
        // Retornar detalle
        return accommodation;
    }
}