package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationImageService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationImageDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para gestionar imágenes de alojamiento
 * representadas por {@link AccommodationImageDTO}.
 *
 * <p>La clase delega la persistencia en {@link AccommodationImageDAO} y no
 * introduce reglas adicionales: su rol es orquestar la llamada al DAO.</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Guardar el registro/metadatos de una imagen: {@link #save(AccommodationImageDTO)}.</li>
 * </ul>
 *
 * @author
 *   Equipo Prg Avanzada
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 * @see AccommodationImageDAO
 * @see AccommodationImageService
 */
@Slf4j
@Service
public class AccommodationImageServiceImpl implements AccommodationImageService {

    private final AccommodationImageDAO dao;

    /**
     * Crea el servicio con su dependencia DAO.
     *
     * @param dao componente de acceso a datos para imágenes de alojamiento (no nulo)
     */
    public AccommodationImageServiceImpl(AccommodationImageDAO dao) {
        this.dao = dao;
    }

    /**
     * Persiste la información de una imagen de alojamiento.
     *
     * <p><b>Nota:</b> La validación y la lógica de persistencia se delegan
     * completamente en el DAO.</p>
     *
     * @param dto DTO de la imagen a guardar (no nulo)
     * @return DTO resultante tras la operación de guardado
     * @throws RuntimeException si el DAO detecta datos inválidos o falla la persistencia
     * @implSpec Delegado directo a {@link AccommodationImageDAO#save(AccommodationImageDTO)}.
     */
    @Override
    public AccommodationImageDTO save(AccommodationImageDTO dto) {
        // Log pre-operación (sin asumir campos específicos del DTO)
        log.debug("Guardando imagen de alojamiento: {}", dto);

        AccommodationImageDTO saved = dao.save(dto);

        // Log post-operación (seguimos sin asumir getters específicos)
        log.info("Imagen de alojamiento guardada: {}", saved);
        return saved;
    }
}
