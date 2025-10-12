package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.QualificationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.QualificationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para gestionar {@link QualificationDTO}.
 *
 * <p>La clase delega la persistencia en {@link QualificationDAO} y no introduce
 * reglas adicionales; su función es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar una calificación: {@link #save(QualificationDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see QualificationDAO
 * @see QualificationService
 */
@Slf4j
@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para calificaciones (no nulo)
     */
    public QualificationServiceImpl(QualificationDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link QualificationDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO de la calificación a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link QualificationDAO#save(QualificationDTO)}.
     */
    @Override
    @Transactional
    public QualificationDTO save(QualificationDTO dto) {
        log.debug("Guardando calificación: {}", dto);
        QualificationDTO saved = dao.save(dto);
        log.info("Calificación guardada: {}", saved);
        return saved;
    }
}
