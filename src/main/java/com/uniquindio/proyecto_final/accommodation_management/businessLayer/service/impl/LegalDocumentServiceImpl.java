package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.LegalDocumentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.LegalDocumentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para gestionar {@link LegalDocumentDTO}.
 *
 * <p>Esta clase delega la persistencia en {@link LegalDocumentDAO} y no introduce
 * reglas adicionales; su función es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar un documento legal: {@link #save(LegalDocumentDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see LegalDocumentDAO
 * @see LegalDocumentService
 */
@Slf4j
@Service
public class LegalDocumentServiceImpl implements LegalDocumentService {

    private final LegalDocumentDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para documentos legales (no nulo)
     */
    public LegalDocumentServiceImpl(LegalDocumentDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link LegalDocumentDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO del documento legal a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link LegalDocumentDAO#save(LegalDocumentDTO)}.
     */
    @Override
    @Transactional
    public LegalDocumentDTO save(LegalDocumentDTO dto) {
        log.debug("Guardando documento legal: {}", dto);
        LegalDocumentDTO saved = dao.save(dto);
        log.info("Documento legal guardado: {}", saved);
        return saved;
    }
}
