package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.CommentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CommentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con comentarios.
 *
 * <p>Se encarga de orquestar las llamadas al DAO sin agregar reglas de negocio adicionales.</p>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see CommentDAO
 * @see CommentService
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    // DAO encargado de la persistencia de los comentarios
    private final CommentDAO commentDAO;

    /**
     * Constructor que inyecta el DAO requerido.
     *
     * @param commentDAO DAO para manejo de datos relacionados con CommentDTO
     */
    public CommentServiceImpl(CommentDAO commentDAO) {
        // Asignación del DAO al atributo de la clase
        this.commentDAO = commentDAO;
    }

    /**
     * Guarda un comentario en la base de datos.
     *
     * @param commentDTO comentario a registrar
     * @return comentario almacenado con identificador asignado
     */
    @Override
    @Transactional
    public CommentDTO save(CommentDTO commentDTO) {

        // Registro en logs para seguimiento
        log.debug("Guardando comentario: {}", commentDTO);

        // Llamada al DAO para almacenar el comentario
        CommentDTO savedComment = commentDAO.save(commentDTO);

        // Confirmación de persistencia en logs
        log.info("Comentario guardado: {}", savedComment);

        // Retorno del comentario persistido
        return savedComment;
    }

    /**
     * Obtiene la lista de comentarios asociados a un alojamiento.
     *
     * @param accommodationId identificador del alojamiento
     * @return lista de comentarios encontrados
     */
    @Override
    public List<CommentDTO> commentsList(int accommodationId) {

        // Registro en logs de seguimiento
        log.debug("Listando comentarios para accommodationId={}", accommodationId);

        // Llamada al DAO para obtener la lista de comentarios
        List<CommentDTO> comments = commentDAO.commentsList(accommodationId);

        // Log resumen del tamaño de la lista obtenida
        log.info("Encontrados {} comentarios para accommodationId={}", comments.size(), accommodationId);

        // Retorno de la lista resultante
        return comments;
    }

    /**
     * Permite responder un comentario.
     *
     * @param commentId id del comentario a responder
     * @param responseData contenido de la respuesta
     * @return null (funcionalidad aún no implementada)
     */
    @Override
    public CommentDTO respondComments(int commentId, CommentDTO responseData) {

        // Registro de la intención de acción (funcionalidad faltante)
        log.debug("Respondiento comentario idComent={} con payload={}", commentId, responseData);

        // Advertencia indicando que aún no está implementado
        log.warn("respondComments(int, CommentDTO) actualmente no tiene implementación");

        // Retorno temporal hasta implementar lógica
        return null;
    }
}