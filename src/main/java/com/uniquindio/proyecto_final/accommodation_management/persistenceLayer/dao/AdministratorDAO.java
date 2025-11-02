package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AdministratorMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link AdministratorDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link AdministratorRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link AdministratorMapper}.</p>
 *
 * <p>Incluye operaciones de guardado, búsqueda por ID y autenticación.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class AdministratorDAO {

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    /**
     * Persiste un {@link AdministratorDTO} en la base de datos.
     *
     * @param dto DTO a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public AdministratorDTO save(AdministratorDTO dto) {
        AdministratorEntity entity = administratorMapper.toEntity(dto);
        AdministratorEntity savedEntity = administratorRepository.save(entity);
        return administratorMapper.toDTO(savedEntity);
    }

    /**
     * Busca un administrador por su ID.
     *
     * @param id identificador del administrador
     * @return {@link Optional} con el DTO si existe; vacío si no
     */
    public Optional<AdministratorDTO> findById(int id) {
        return administratorRepository.findById(id)
                .map(administratorMapper::toDTO);
    }

    /**
     * Autentica un administrador usando su correo electrónico.
     *
     * <p>No valida la contraseña aquí; solo obtiene el administrador por email.
     * La validación de credenciales se realiza en el servicio.</p>
     *
     * @param login DTO con credenciales de login
     * @return {@link AdministratorDTO} si se encuentra el correo; {@code null} si no existe
     */
    public AdministratorDTO login(LoginDTO login) {
        AdministratorEntity entity = administratorRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        return administratorMapper.toDTO(entity);
    }
}