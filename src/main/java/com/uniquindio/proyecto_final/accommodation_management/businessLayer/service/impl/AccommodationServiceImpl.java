package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de negocio para gestionar objetos {@link AccommodationDTO}.
 *
 * <p>Esta clase aplica reglas muy simples (actualizar precio, soft-delete) y
 * delega la persistencia en {@link AccommodationDAO}.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear alojamientos: {@link #save(AccommodationDTO)}.</li>
 *   <li>Consultar disponibles: {@link #searchAvailableAccommodations()}.</li>
 *   <li>Listar por anfitrión: {@link #ownAccommodationList(int)}.</li>
 *   <li>Editar precio: {@link #edit(int, AccommodationDTO)}.</li>
 *   <li>Inactivar (soft delete): {@link #delete(int)}.</li>
 *   <li>Detalle: {@link #detail(int)}.</li>
 * </ul>
 *
 * @author
 *   Equipo Prg Avanzada
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see AccommodationDAO
 * @see AccommodationService
 */
@Slf4j
@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos (no nulo)
     */
    public AccommodationServiceImpl(AccommodationDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un alojamiento.
     *
     * @param dto DTO de entrada (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación/persistencia
     * @implSpec Delegado directo a {@link AccommodationDAO#save(AccommodationDTO)}.
     */
    @Override
    @Transactional
    public AccommodationDTO save(AccommodationDTO dto) {
        log.debug("Guardando alojamiento: {}", dto);
        AccommodationDTO saved = dao.save(dto);
        log.info("Alojamiento guardado: {}", saved);
        return saved;
    }

    /**
     * Obtiene una lista de alojamientos disponibles.
     * @return lista no nula (posiblemente vacía)
     * @implSpec Delegado directo a {@link AccommodationDAO#searchAvailableAccommodations()}.
     */
    @Override
    public List<AccommodationDTO> searchAvailableAccommodations() {
        log.debug("Buscando alojamientos disponibles");
        List<AccommodationDTO> list = dao.searchAvailableAccommodations();
        log.info("Encontrados {} alojamientos disponibles", list.size());
        return list;
    }

    /**
     * Lista los alojamientos de un anfitrión.
     * @param idHost identificador del anfitrión
     * @return lista no nula (posiblemente vacía)
     * @implSpec Delegado directo a {@link AccommodationDAO#ownAccommodationList(int)}.
     */
    @Override
    public List<AccommodationDTO> ownAccommodationList(int idHost) {
        log.debug("Listando alojamientos del hostId={}", idHost);
        List<AccommodationDTO> list = dao.ownAccommodationList(idHost);
        log.info("Host {} tiene {} alojamientos", idHost, list.size());
        return list;
    }

    /**
     * Edita un alojamiento actualizando <b>solo el precio</b>.
     *
     * <p>Si el alojamiento no existe, se retorna {@link Optional#empty()}.</p>
     *
     * @param id identificador del alojamiento a editar
     * @param accommodation datos entrantes (se usa él {@code price})
     * @return {@link Optional} con el DTO actualizado si existe; vacío en caso contrario
     * @implSpec
     *   <ul>
     *     <li>Busca por ID; si existe, copia {@code price} y guarda.</li>
     *   </ul>
     */
    @Transactional
    @Override
    public Optional<AccommodationDTO> edit(int id, AccommodationDTO accommodation) {
        log.debug("Editando alojamiento id={} con newPrice={}", id, accommodation.getPrice());
        Optional<AccommodationDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            AccommodationDTO acc = accommodationDb.orElseThrow();
            acc.setPrice(accommodation.getPrice());
            acc.setDepartmentsId(accommodation.getDepartmentsId());
            acc.setCitiesId(accommodation.getCitiesId());
            acc.setDetailedDescription(accommodation.getDetailedDescription());
            acc.setExactLocation(accommodation.getExactLocation());
            acc.setMaximumCapacity(accommodation.getMaximumCapacity());
            acc.setServices(accommodation.getServices());
            acc.setImgUrl(accommodation.getImgUrl());
            AccommodationDTO updated = dao.save(acc);
            log.info("Alojamiento id={} actualizado (price={})", id, updated.getPrice());
            return Optional.of(updated);
        }
        log.warn("No se encontró alojamiento id={} para editar", id);
        return accommodationDb;
    }

    /**
     * Realiza un <b>soft delete</b> (establece {@code active=false}).
     *
     * <p>Si el alojamiento no existe, se retorna {@link Optional#empty()}.</p>
     *
     * @param id identificador del alojamiento a inactivar
     * @return {@link Optional} con el DTO inactivado si existe; vacío en caso contrario
     * @implSpec
     *   <ul>
     *     <li>Busca por ID; si existe, marca {@code active=false} y guarda.</li>
     *   </ul>
     */
    @Transactional
    @Override
    public Optional<AccommodationDTO> delete(int id) {
        log.debug("Inactivando (soft delete) alojamiento id={}", id);
        Optional<AccommodationDTO> accommodationDb = dao.findById(id);
        if (accommodationDb.isPresent()) {
            AccommodationDTO acc = accommodationDb.orElseThrow();
            acc.setActive(false);
            AccommodationDTO saved = dao.save(acc);
            log.info("Alojamiento id={} inactivado", id);
            return Optional.of(saved);
        }
        log.warn("No se encontró alojamiento id={} para inactivar", id);
        return accommodationDb;
    }

    /**
     * Obtiene el detalle de un alojamiento.
     * @param accommodationId identificador del alojamiento
     * @return el DTO sí existe; {@code null} en caso contrario
     * @implSpec Delegado directo a {@link AccommodationDAO#findById(int)}.
     */
    @Override
    public AccommodationDTO detail(int accommodationId) {
        log.debug("Consultando detalle de alojamiento id={}", accommodationId);
        AccommodationDTO dto = dao.findById(accommodationId).orElse(null);
        log.info("Detalle id={} {}", accommodationId, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }
}
