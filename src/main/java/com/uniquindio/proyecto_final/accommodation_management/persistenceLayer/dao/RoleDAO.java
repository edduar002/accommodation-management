package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.RoleDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.RoleEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.RoleMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link RoleDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link RoleRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link RoleMapper}.</p>
 *
 * <p>Incluye operaciones de creación de roles, listado de todos los roles
 * y búsqueda de roles por ID.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class RoleDAO {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    /**
     * Persiste un rol en la base de datos.
     *
     * @param dto DTO del rol a guardar
     * @return DTO del rol guardado
     */
    public RoleDTO save(RoleDTO dto) {
        RoleEntity entity = roleMapper.toEntity(dto);
        RoleEntity savedEntity = roleRepository.save(entity);
        return roleMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene todos los roles disponibles en la base de datos.
     *
     * @return Lista de {@link RoleDTO} con todos los roles
     */
    public List<RoleDTO> rolesList() {
        List<RoleEntity> entities = roleRepository.allRoles();
        return entities.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un rol por su ID.
     *
     * @param id ID del rol
     * @return {@link Optional} con el DTO del rol si existe, vacío en caso contrario
     */
    public Optional<RoleDTO> findById(int id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDTO);
    }
}