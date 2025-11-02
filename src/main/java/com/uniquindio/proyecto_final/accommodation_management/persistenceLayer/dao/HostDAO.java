package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.HostMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link HostDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link HostRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link HostMapper}.</p>
 *
 * <p>Incluye operaciones de guardado, listado, búsqueda por ID y autenticación de hosts.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class HostDAO {

    private final HostRepository hostRepository;
    private final HostMapper hostMapper;

    /**
     * Persiste un {@link HostDTO} en la base de datos.
     *
     * @param dto DTO del host a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public HostDTO save(HostDTO dto) {
        HostEntity entity = hostMapper.toEntity(dto);
        HostEntity savedEntity = hostRepository.save(entity);
        return hostMapper.toDTO(savedEntity);
    }

    /**
     * Busca un host por su identificador.
     *
     * @param id identificador del host
     * @return {@link Optional} con el DTO si se encuentra; vacío si no existe
     */
    public Optional<HostDTO> findById(int id) {
        return hostRepository.findById(id)
                .map(hostMapper::toDTO);
    }

    /**
     * Autentica a un host por correo electrónico.
     *
     * @param login DTO con email y contraseña del host
     * @return DTO del host si existe; {@code null} si no se encuentra
     */
    public HostDTO login(LoginDTO login) {
        HostEntity entity = hostRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        return hostMapper.toDTO(entity);
    }

    /**
     * Obtiene la lista de todos los hosts registrados.
     *
     * @return lista de {@link HostDTO} presentes en la base de datos
     */
    public List<HostDTO> hostsList() {
        List<HostEntity> entities = hostRepository.allHosts();
        return entities.stream()
                .map(hostMapper::toDTO)
                .collect(Collectors.toList());
    }
}