package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad HostEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y métodos personalizados de búsqueda de hosts.
 */
public interface HostRepository extends JpaRepository<HostEntity, Integer> {

    /**
     * Busca un host por su correo electrónico.
     * @param email correo electrónico del host.
     * @return entidad HostEntity correspondiente o null si no existe.
     */
    // Método derivado de Spring Data para buscar host por email
    HostEntity findByEmail(String email);

    /**
     * Obtiene todos los hosts activos.
     * @return lista de hosts activos.
     */
    // Consulta que selecciona todos los hosts donde active = true
    @Query("SELECT a FROM HostEntity a WHERE a.active = true")
    List<HostEntity> allHosts();

}