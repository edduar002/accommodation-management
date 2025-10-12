package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementación del servicio de negocio para gestionar {@link ReservationDTO}.
 *
 * <p>Aplica reglas simples (respuestas HTTP según resultado) y delega
 * la persistencia/consultas en {@link ReservationDAO}.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Crear reservas: {@link #save(ReservationDTO)}.</li>
 *   <li>Ver reservas de un alojamiento: {@link #viewAccommodationReservations(int)}.</li>
 *   <li>Hacer reservas (stub): {@link #makeReservations(LocalDate, LocalDate)}.</li>
 *   <li>Cancelar, aceptar y rechazar solicitudes: {@link #cancelReservations(int)},
 *       {@link #acceptReservationRequests(int)}, {@link #rejectReservationRequests(int)}.</li>
 *   <li>Ver historial y detalle: {@link #viewReservationHistory(int)},
 *       {@link #viewReservationDetails(int)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see ReservationDAO
 * @see ReservationService
 */
@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para reservas (no nulo)
     */
    public ReservationServiceImpl(ReservationDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste una reserva.
     * @param dto DTO de la reserva (no nulo)
     * @return DTO persistido
     * @implSpec Delegado directo a {@link ReservationDAO#save(ReservationDTO)}.
     */
    @Override
    @Transactional
    public ReservationDTO save(ReservationDTO dto) {
        log.debug("Guardando reserva: {}", dto);
        ReservationDTO saved = dao.save(dto);
        log.info("Reserva guardada: {}", saved);
        return saved;
    }

    /**
     * Lista reservas asociadas a un alojamiento.
     * @param idAccommodation id del alojamiento
     * @return lista no nula (posiblemente vacía)
     */
    @Override
    public List<ReservationDTO> viewAccommodationReservations(int idAccommodation) {
        log.debug("Consultando reservas de accommodationId={}", idAccommodation);
        List<ReservationDTO> list = dao.viewAccommodationReservations(idAccommodation);
        log.info("Encontradas {} reservas para accommodationId={}", list.size(), idAccommodation);
        return list;
    }

    /**
     * Realiza una reserva entre fechas dadas.
     * <p><b>Stub:</b> actualmente retorna {@code null}.</p>
     */
    @Override
    public ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut) {
        log.warn("makeReservations(checkIn={}, checkOut={}) aún no implementado", checkIn, checkOut);
        return null;
    }

    /**
     * Cancela una reserva por id.
     * @param idReservation id de la reserva
     * @return 404 si no existe; 200 con el cuerpo si se canceló
     */
    @Override
    public ResponseEntity<ReservationDTO> cancelReservations(int idReservation) {
        log.debug("Cancelando reserva id={}", idReservation);
        ReservationDTO updatedReservation = dao.cancelReservations(idReservation);
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para cancelar", idReservation);
            return ResponseEntity.notFound().build();
        }
        log.info("Reserva id={} cancelada", idReservation);
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Historial de reservas de un usuario.
     * @param idUser id del usuario
     * @return lista de reservas (posiblemente vacía)
     */
    @Override
    public List<ReservationDTO> viewReservationHistory(int idUser) {
        log.debug("Consultando historial de reservas de userId={}", idUser);
        List<ReservationDTO> list = dao.viewReservationHistory(idUser);
        log.info("Historial para userId={} → {} reservas", idUser, list.size());
        return list;
    }

    /**
     * Detalle de una reserva por id.
     * @param idReservation id de la reserva
     * @return DTO si existe; {@code null} si no
     */
    @Override
    public ReservationDTO viewReservationDetails(int idReservation) {
        log.debug("Detalle de reserva id={}", idReservation);
        ReservationDTO dto = dao.findById(idReservation).orElse(null);
        log.info("Detalle reserva id={} {}", idReservation, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    /**
     * Acepta una solicitud de reserva.
     * @param idReservation id de la reserva
     * @return 404 si no existe; 200 con el cuerpo si se aceptó
     */
    @Override
    public ResponseEntity<ReservationDTO> acceptReservationRequests(int idReservation) {
        log.debug("Aceptando solicitud de reserva id={}", idReservation);
        ReservationDTO updatedReservation = dao.acceptReservationRequests(idReservation);
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para aceptar", idReservation);
            return ResponseEntity.notFound().build();
        }
        log.info("Reserva id={} aceptada", idReservation);
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Rechaza una solicitud de reserva.
     * @param idReservation id de la reserva
     * @return 404 si no existe; 200 con el cuerpo si se rechazó
     */
    @Override
    public ResponseEntity<ReservationDTO> rejectReservationRequests(int idReservation) {
        log.debug("Rechazando solicitud de reserva id={}", idReservation);
        ReservationDTO updatedReservation = dao.rejectReservationRequests(idReservation);
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para rechazar", idReservation);
            return ResponseEntity.notFound().build();
        }
        log.info("Reserva id={} rechazada", idReservation);
        return ResponseEntity.ok(updatedReservation);
    }
}
