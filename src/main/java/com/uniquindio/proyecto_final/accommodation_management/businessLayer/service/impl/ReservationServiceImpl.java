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
 */
@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    // DAO para operaciones sobre reservas
    private final ReservationDAO dao;

    /**
     * Constructor con inyección por parámetros.
     */
    public ReservationServiceImpl(ReservationDAO dao) {
        // Asignar dependencia DAO
        this.dao = dao;
    }

    @Override
    @Transactional
    public ReservationDTO save(ReservationDTO dto) {
        // Registrar intento de guardado
        log.debug("Guardando reserva: {}", dto);

        // Guardar en BD delegando al DAO
        ReservationDTO saved = dao.save(dto);

        // Log de éxito
        log.info("Reserva guardada: {}", saved);

        // Retornar reserva guardada
        return saved;
    }

    @Override
    public List<ReservationDTO> viewAccommodationReservations(int idAccommodation) {
        // Registrar consulta
        log.debug("Consultando reservas de accommodationId={}", idAccommodation);

        // Obtener reservas desde el DAO
        List<ReservationDTO> list = dao.viewAccommodationReservations(idAccommodation);

        // Log cantidad encontrada
        log.info("Encontradas {} reservas para accommodationId={}", list.size(), idAccommodation);

        // Retornar lista
        return list;
    }

    @Override
    public ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut) {
        // Avisar que aún no está implementado
        log.warn("makeReservations(checkIn={}, checkOut={}) aún no implementado", checkIn, checkOut);

        // Retornar null temporalmente
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> cancelReservations(int idReservation) {
        // Log de intento de cancelación
        log.debug("Cancelando reserva id={}", idReservation);

        // Delegar cancelación en el DAO
        ReservationDTO updatedReservation = dao.cancelReservations(idReservation);

        // Si no existe, responder 404
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para cancelar", idReservation);
            return ResponseEntity.notFound().build();
        }

        // Log de éxito
        log.info("Reserva id={} cancelada", idReservation);

        // Responder con la reserva modificada
        return ResponseEntity.ok(updatedReservation);
    }

    @Override
    public List<ReservationDTO> viewReservationHistory(int idUser) {
        // Registrar consulta
        log.debug("Consultando historial de reservas de userId={}", idUser);

        // Obtener historial desde DAO
        List<ReservationDTO> list = dao.viewReservationHistory(idUser);

        // Log cantidad encontrada
        log.info("Historial para userId={} → {} reservas", idUser, list.size());

        // Retornar historial
        return list;
    }

    @Override
    public ReservationDTO viewReservationDetails(int idReservation) {
        // Registrar consulta
        log.debug("Detalle de reserva id={}", idReservation);

        // Buscar en base de datos
        ReservationDTO dto = dao.findById(idReservation).orElse(null);

        // Log según resultado
        log.info("Detalle reserva id={} {}", idReservation, (dto != null ? "encontrado" : "no encontrado"));

        // Retornar detalle o null
        return dto;
    }

    @Override
    public ResponseEntity<ReservationDTO> acceptReservationRequests(int idReservation) {
        // Registrar intento de aceptación
        log.debug("Aceptando solicitud de reserva id={}", idReservation);

        // Delegar al DAO
        ReservationDTO updatedReservation = dao.acceptReservationRequests(idReservation);

        // Si no existe → responder 404
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para aceptar", idReservation);
            return ResponseEntity.notFound().build();
        }

        // Log de éxito
        log.info("Reserva id={} aceptada", idReservation);

        // Retornar respuesta exitosa
        return ResponseEntity.ok(updatedReservation);
    }

    @Override
    public ResponseEntity<ReservationDTO> rejectReservationRequests(int idReservation) {
        // Registrar intento de rechazo
        log.debug("Rechazando solicitud de reserva id={}", idReservation);

        // Delegar operación en el DAO
        ReservationDTO updatedReservation = dao.rejectReservationRequests(idReservation);

        // Si no existe → responder 404
        if (updatedReservation == null) {
            log.warn("No se encontró reserva id={} para rechazar", idReservation);
            return ResponseEntity.notFound().build();
        }

        // Log de éxito
        log.info("Reserva id={} rechazada", idReservation);

        // Responder con entidad actualizada
        return ResponseEntity.ok(updatedReservation);
    }
}