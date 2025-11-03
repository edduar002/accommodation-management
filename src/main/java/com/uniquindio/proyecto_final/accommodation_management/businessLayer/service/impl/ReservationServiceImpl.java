package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private final ReservationDAO reservationDAO;

    /**
     * Constructor con inyección por parámetros.
     */
    public ReservationServiceImpl(ReservationDAO dao, ReservationDAO reservationDAO) {
        this.dao = dao;
        this.reservationDAO = reservationDAO;
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
    public Optional<ReservationDTO> changeStatus(int idReservation, ReservationDTO departmentData) {

        // Registro de acción
        log.debug("Editando reserva id={} con nuevo estado={}", idReservation, departmentData.getState());

        // Búsqueda del registro original
        Optional<ReservationDTO> departmentDb = reservationDAO.findById(idReservation);

        // Si existe, actualizar
        if (departmentDb.isPresent()) {

            // Obtención del registro original
            ReservationDTO departmentToUpdate = departmentDb.get();

            // Actualización del nombre
            departmentToUpdate.setState(departmentData.getState());

            // Guardar cambios
            ReservationDTO updatedDepartment = reservationDAO.save(departmentToUpdate);

            // Confirmación
            log.info("Reserva id={} actualizada correctamente", idReservation);

            // Retorno del actualizado
            return Optional.of(updatedDepartment);
        }

        // Si no existe, notificar en logs
        log.warn("No se encontró departamento id={} para editar", idReservation);

        // Retorno del Optional vacío
        return departmentDb;
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