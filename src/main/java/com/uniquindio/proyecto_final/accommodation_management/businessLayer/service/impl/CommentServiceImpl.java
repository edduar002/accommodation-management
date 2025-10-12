package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CommentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio de negocio para gestionar {@link CommentDTO}.
 *
 * <p>La clase delega la persistencia en {@link CommentDAO} y, por ahora,
 * mantiene métodos de consulta/respuesta como “stub” (sin lógica adicional).</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar comentarios: {@link #save(CommentDTO)}.</li>
 *   <li>Listar comentarios de un alojamiento (stub): {@link #commentsList(int)}.</li>
 *   <li>Responder comentarios (stub): {@link #respondComments(int, CommentDTO)}.</li>
 * </ul>
 *
 * <p><b>Nota:</b> los métodos marcados como “stub” podrán ser implementados
 * en el futuro con reglas de negocio y consultas al DAO.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see CommentDAO
 * @see CommentService
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para comentarios (no nulo)
     */
    public CommentServiceImpl(CommentDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un comentario.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring.</p>
     *
     * @param dto comentario a guardar (no nulo)
     * @return comentario guardado (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación/persistencia
     */
    @Override
    @Transactional
    public CommentDTO save(CommentDTO dto) {
        log.debug("Guardando comentario: {}", dto);
        CommentDTO saved = dao.save(dto);
        log.info("Comentario guardado: {}", saved);
        return saved;
    }

    /**
     * Listo comentario de un alojamiento.
     *
     * <p><b>Stub:</b> por ahora retorna una lista vacía.</p>
     *
     * @param idAccommodation identificador del alojamiento
     * @return lista vacía
     */
    @Override
    public List<CommentDTO> commentsList(int idAccommodation) {
        log.debug("Listando comentarios de accommodationId={}", idAccommodation);
        log.warn("commentsList(int) actualmente retorna lista vacía (stub)");
        return List.of();
    }

    /**
     * Responde un comentario dado su ID.
     *
     * <p><b>Stub:</b> por ahora retorna {@code null}.</p>
     *
     * @param idComent identificador del comentario a responder
     * @param comment contenido de la respuesta
     * @return {@code null} (pendiente de implementación)
     */
    @Override
    public CommentDTO respondComments(int idComent, CommentDTO comment) {
        log.debug("Respondiento comentario idComent={} con payload={}", idComent, comment);
        log.warn("respondComments(int, CommentDTO) actualmente retorna null (stub)");
        return null;
    }
}
