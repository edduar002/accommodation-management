package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ResponseService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ResponseCommentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio de negocio para gestionar {@link ResponseDTO}
 * (respuestas a comentarios).
 *
 * <p>La clase delega la persistencia en {@link ResponseCommentDAO} y no introduce
 * reglas adicionales; su función es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar una respuesta a comentario: {@link #save(ResponseDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see ResponseCommentDAO
 * @see ResponseService
 */
@Slf4j
@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseCommentDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para respuestas de comentario (no nulo)
     */
    public ResponseServiceImpl(ResponseCommentDAO dao) {
        this.dao = dao;
        log.debug("ResponseServiceImpl inicializado con DAO={}", dao.getClass().getSimpleName());
    }

    @Override
    public List<ResponseDTO> getByComment(int idHost) {
        log.debug("Listando alojamientos del hostId={}", idHost);
        List<ResponseDTO> list = dao.getByComment(idHost);
        log.info("Host {} tiene {} alojamientos", idHost, list.size());
        return list;
    }

    /**
     * Persiste un {@link ResponseDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO de la respuesta a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link ResponseCommentDAO#save(ResponseDTO)}.
     */
    @Override
    @Transactional
    public ResponseDTO save(ResponseDTO dto) {
        log.debug("Guardando response: {}", dto);
        ResponseDTO saved = dao.save(dto);
        log.info("Response guardado: {}", saved);
        return saved;
    }
}
