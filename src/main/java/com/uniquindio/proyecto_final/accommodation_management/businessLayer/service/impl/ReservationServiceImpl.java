package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        this.dao = dao;
    }

    /**
     * Guarda una nueva reserva en la base de datos.
     * Registra logs de depuración y éxito.
     *
     * @param dto Objeto ReservationDTO a guardar
     * @return Reserva guardada
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
     * Obtiene el historial de reservas de un usuario específico.
     * Consulta al DAO y retorna la lista de reservas encontradas.
     *
     * @param idUser ID del usuario
     * @return Lista de ReservationDTO con el historial de reservas
     */
    @Override
    public List<ReservationDTO> viewReservationHistory(int idUser) {
        log.debug("Consultando historial de reservas de userId={}", idUser);
        List<ReservationDTO> list = dao.viewReservationHistory(idUser);
        log.info("Historial para userId={} → {} reservas", idUser, list.size());
        return list;
    }

    /**
     * Consulta el detalle de una reserva específica por su ID.
     *
     * @param idReservation ID de la reserva
     * @return ReservationDTO con los detalles o null si no existe
     */
    @Override
    public ReservationDTO viewReservationDetails(int idReservation) {
        log.debug("Detalle de reserva id={}", idReservation);
        ReservationDTO dto = dao.findById(idReservation).orElse(null);
        log.info("Detalle reserva id={} {}", idReservation, (dto != null ? "encontrado" : "no encontrado"));
        return dto;
    }

    /**
     * Cambia el estado de una reserva de forma genérica.
     * Si la reserva no existe, retorna 404 Not Found.
     * Ejemplo de alternancia de estados: Pendiente → Aprobado → Realizado → Pendiente.
     *
     * @param idReservation ID de la reserva
     * @return ResponseEntity con la reserva actualizada o 404 si no existe
     */
    @Override
    @Transactional
    public ResponseEntity<ReservationDTO> changeStatus(int idReservation) {
        log.debug("Cambiando estado de reserva id={}", idReservation);

        ReservationDTO dto = dao.findById(idReservation).orElse(null);
        if (dto == null) {
            log.warn("Reserva id={} no encontrada para cambiar estado", idReservation);
            return ResponseEntity.notFound().build();
        }

        String nuevoEstado = switch (dto.getState()) {
            case "Pendiente" -> "Aprobado";
            case "Aprobado" -> "Realizado";
            case "Realizado" -> "Pendiente";
            default -> "Pendiente";
        };
        dto.setState(nuevoEstado);

        dao.save(dto);
        log.info("Estado de reserva id={} actualizado a '{}'", idReservation, nuevoEstado);

        return ResponseEntity.ok(dto);
    }

    /**
     * Lista todas las reservas asociadas a un anfitrión.
     * Retorna una lista vacía si no hay reservas.
     *
     * @param idHost ID del anfitrión
     * @return Lista de ReservationDTO
     */
    @Override
    public List<ReservationDTO> viewReservations(int idHost) {
        log.debug("Listando reservas para hostId={}", idHost);
        List<ReservationDTO> list = dao.viewReservations(idHost);
        log.info("HostId={} → {} reservas encontradas", idHost, list.size());
        return list;
    }
}