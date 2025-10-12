package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ImageDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para gestionar {@link ImageDTO}.
 *
 * <p>La clase delega la persistencia en {@link ImageDAO} y no introduce
 * reglas adicionales; su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar una imagen: {@link #save(ImageDTO)}.</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see ImageDAO
 * @see ImageService
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     * @param dao componente de acceso a datos para imágenes (no nulo)
     */
    public ImageServiceImpl(ImageDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste un {@link ImageDTO}.
     *
     * <p><b>Transaccional:</b> la operación se ejecuta dentro de una transacción
     * administrada por Spring. La validación/persistencia específica se delega
     * completamente al DAO.</p>
     *
     * @param dto DTO de la imagen a guardar (no nulo)
     * @return DTO persistido (normalmente con identificador asignado)
     * @throws RuntimeException si el DAO reporta error de validación o persistencia
     * @implSpec Delegado directo a {@link ImageDAO#save(ImageDTO)}.
     */
    @Override
    @Transactional
    public ImageDTO save(ImageDTO dto) {
        log.debug("Guardando imagen: {}", dto);
        ImageDTO saved = dao.save(dto);
        log.info("Imagen guardada: {}", saved);
        return saved;
    }
}
