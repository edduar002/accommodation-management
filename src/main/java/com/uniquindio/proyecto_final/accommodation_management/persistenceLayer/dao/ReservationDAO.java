package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ReservationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link ReservationDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link ReservationRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link ReservationMapper}.</p>
 *
 * <p>Incluye operaciones de creación, consulta, historial, cancelación y
 * aceptación/rechazo de reservas.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class ReservationDAO {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    /**
     * Persiste una reserva en la base de datos.
     *
     * @param dto DTO de la reserva a guardar
     * @return DTO de la reserva guardada
     */
    public ReservationDTO save(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity savedEntity = reservationRepository.save(entity);
        return reservationMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene todas las reservas de un alojamiento específico.
     *
     * @param idAccommodation ID del alojamiento
     * @return Lista de {@link ReservationDTO} de ese alojamiento
     */
    public List<ReservationDTO> viewAccommodationReservations(int idAccommodation) {
        List<ReservationEntity> entities = reservationRepository.viewAccommodationReservations(idAccommodation);
        return entities.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el historial de reservas de un usuario.
     *
     * @param idUser ID del usuario
     * @return Lista de {@link ReservationDTO} del historial de reservas
     */
    public List<ReservationDTO> viewReservationHistory(int idUser) {
        List<ReservationEntity> entities = reservationRepository.viewReservationHistory(idUser);
        return entities.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el historial de reservas de un anfitrion.
     *
     * @param idHost ID del anfitrion
     * @return Lista de {@link ReservationDTO} del historial de reservas
     */
    public List<ReservationDTO> viewReservations(int idHost) {
        List<ReservationEntity> entities = reservationRepository.viewReservations(idHost);
        return entities.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca una reserva por su ID.
     *
     * @param id ID de la reserva
     * @return {@link Optional} con el DTO de la reserva si se encuentra
     */
    public Optional<ReservationDTO> findById(int id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDTO);
    }

}