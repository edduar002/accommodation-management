package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.UserMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link UserDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link UserRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link UserMapper}.</p>
 *
 * <p>Incluye operaciones de creación de usuarios, login, listado de usuarios
 * y búsqueda de usuarios por ID.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Persiste un usuario en la base de datos.
     *
     * @param dto DTO del usuario a guardar
     * @return DTO del usuario guardado
     */
    public UserDTO save(UserDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDTO(savedEntity);
    }

    /**
     * Busca un usuario por email para realizar el login.
     *
     * @param login DTO con el email y contraseña del usuario
     * @return DTO del usuario si existe, null si no se encuentra
     */
    public UserDTO login(LoginDTO login) {
        UserEntity entity = userRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        return userMapper.toDTO(entity);
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id ID del usuario
     * @return {@link Optional} con el DTO del usuario si existe, vacío en caso contrario
     */
    public Optional<UserDTO> findById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     *
     * @return Lista de {@link UserDTO} con todos los usuarios
     */
    public List<UserDTO> usersList() {
        List<UserEntity> entities = userRepository.allUsers();
        return entities.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}