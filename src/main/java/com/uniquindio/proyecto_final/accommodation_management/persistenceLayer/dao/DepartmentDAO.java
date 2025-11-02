package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.DepartmentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.DepartmentMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Componente de acceso a datos (DAO) para gestionar {@link DepartmentDTO}.
 *
 * <p>Se encarga de interactuar con la base de datos a través del
 * {@link DepartmentRepository} y mapear las entidades a DTOs y viceversa
 * usando {@link DepartmentMapper}.</p>
 *
 * <p>Incluye operaciones de guardado, listado y búsqueda por ID de departamentos.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Repository
@RequiredArgsConstructor
public class DepartmentDAO {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    /**
     * Persiste un {@link DepartmentDTO} en la base de datos.
     *
     * @param dto DTO del departamento a guardar (no nulo)
     * @return DTO guardado, normalmente con ID asignado
     */
    public DepartmentDTO save(DepartmentDTO dto) {
        DepartmentEntity entity = departmentMapper.toEntity(dto);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return departmentMapper.toDTO(savedEntity);
    }

    /**
     * Obtiene la lista de todos los departamentos activos.
     *
     * @return lista de {@link DepartmentDTO} presentes en la base de datos
     */
    public List<DepartmentDTO> departmentsList() {
        List<DepartmentEntity> entities = departmentRepository.allDepartments();
        return entities.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un departamento por su identificador.
     *
     * @param id identificador del departamento
     * @return {@link Optional} con el DTO si se encuentra; vacío si no existe
     */
    public Optional<DepartmentDTO> findById(int id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDTO);
    }
}