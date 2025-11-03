package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad ReservationEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y consultas personalizadas para reservas.
 */
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    /**
     * Obtiene el historial de reservas de un usuario específico.
     * @param idUser ID del usuario.
     * @return lista de reservas del usuario.
     */
    // Consulta que selecciona reservas filtradas por ID de usuario
    @Query("""
    SELECT r
    FROM ReservationEntity r
    JOIN FETCH r.accommodation a
    JOIN FETCH a.city c
    WHERE r.usersId = :idUser
""")
    List<ReservationEntity> viewReservationHistory(@Param("idUser") int idUser);

    /**
     * Obtiene el historial de reservas de un anfitrion específico.
     * @param idHost ID del anfitrion.
     * @return lista de reservas del anfitrion.
     */
    // Consulta que selecciona reservas filtradas por ID de host
    @Query("""
    SELECT r
    FROM ReservationEntity r
    JOIN FETCH r.accommodation a
    JOIN FETCH a.city c
    WHERE r.hostsId = :idHost
""")
    List<ReservationEntity> viewReservations(@Param("idHost") int idHost);

}