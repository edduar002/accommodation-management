package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.DepartmentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.DepartmentService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.DepartmentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para operaciones sobre departamentos.
 *
 * <p>Orquesta la interacción con {@link DepartmentDAO} sin añadir lógica adicional.</p>
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    // DAO encargado de la persistencia de datos de Departamentos
    private final DepartmentDAO departmentDAO;

    /**
     * Constructor con inyección del DAO.
     * @param departmentDAO DAO para manejar datos de departamentos
     */
    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        // Asignación del DAO recibido a la propiedad interna
        this.departmentDAO = departmentDAO;
    }

    /**
     * Guarda un nuevo departamento en la base de datos.
     *
     * @param departmentDTO datos del departamento a guardar
     * @return departamento guardado con id asignado
     */
    @Override
    @Transactional
    public DepartmentDTO save(DepartmentDTO departmentDTO) {

        // Registro debug para trazabilidad
        log.debug("Guardando departamento: {}", departmentDTO);

        // Llamada al DAO para persistir
        DepartmentDTO savedDepartment = departmentDAO.save(departmentDTO);

        // Confirmación de guardado
        log.info("Departamento guardado: {}", savedDepartment);

        // Retorno del resultado
        return savedDepartment;
    }

    /**
     * Obtiene la lista completa de departamentos.
     *
     * @return lista de departamentos
     */
    @Override
    public List<DepartmentDTO> departmentsList() {

        // Registro de inicio de consulta
        log.debug("Consultando lista completa de departamentos");

        // Llamada al DAO
        List<DepartmentDTO> departmentList = departmentDAO.departmentsList();

        // Registro del total encontrado
        log.info("Encontrados {} departamentos", departmentList.size());

        // Retorno de la lista
        return departmentList;
    }

    /**
     * Busca el detalle de un departamento según su id.
     *
     * @param departmentId id del departamento a consultar
     * @return departamento encontrado o null si no existe
     */
    @Override
    public DepartmentDTO detail(int departmentId) {

        // Registro de la consulta
        log.debug("Consultando detalle de departamento id={}", departmentId);

        // Búsqueda en el DAO
        DepartmentDTO departmentDTO = departmentDAO.findById(departmentId).orElse(null);

        // Registro de resultado
        log.info("Detalle departamento id={} {}", departmentId, (departmentDTO != null ? "encontrado" : "no encontrado"));

        // Retorno del resultado
        return departmentDTO;
    }

    /**
     * Edita datos básicos de un departamento.
     *
     * @param id id del departamento a actualizar
     * @param departmentData nuevos datos (solo se usa el nombre)
     * @return departamento actualizado si existe, vacío si no
     */
    @Override
    @Transactional
    public Optional<DepartmentDTO> edit(int id, DepartmentDTO departmentData) {

        // Registro de acción
        log.debug("Editando departamento id={} con nuevo nombre={}", id, departmentData.getName());

        // Búsqueda del registro original
        Optional<DepartmentDTO> departmentDb = departmentDAO.findById(id);

        // Si existe, actualizar
        if (departmentDb.isPresent()) {

            // Obtención del registro original
            DepartmentDTO departmentToUpdate = departmentDb.get();

            // Actualización del nombre
            departmentToUpdate.setName(departmentData.getName());

            // Guardar cambios
            DepartmentDTO updatedDepartment = departmentDAO.save(departmentToUpdate);

            // Confirmación
            log.info("Departamento id={} actualizado correctamente", id);

            // Retorno del actualizado
            return Optional.of(updatedDepartment);
        }

        // Si no existe, notificar en logs
        log.warn("No se encontró departamento id={} para editar", id);

        // Retorno del Optional vacío
        return departmentDb;
    }

    /**
     * Realiza un soft delete (marcar como inactivo) en un departamento.
     *
     * @param id id del departamento a desactivar
     * @return departamento actualizado si existe, vacío si no
     */
    @Override
    @Transactional
    public Optional<DepartmentDTO> delete(int id) {

        // Registro de acción
        log.debug("Inactivando departamento id={}", id);

        // Búsqueda del registro original
        Optional<DepartmentDTO> departmentDb = departmentDAO.findById(id);

        // Si existe, hacer soft delete
        if (departmentDb.isPresent()) {

            // Obtener el registro existente
            DepartmentDTO department = departmentDb.get();

            // Cambiar estado a inactivo
            department.setActive(false);

            // Guardar cambios
            DepartmentDTO updatedDepartment = departmentDAO.save(department);

            // Confirmación
            log.info("Departamento id={} inactivado correctamente", id);

            // Retorno del actualizado
            return Optional.of(updatedDepartment);
        }

        // Log cuando no se encuentra el registro
        log.warn("No se encontró departamento id={} para inactivar", id);

        // Retorno del Optional vacío
        return departmentDb;
    }
}