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
     * Obtiene todas las reservas de un alojamiento específico.
     * @param idAccommodation ID del alojamiento.
     * @return lista de reservas del alojamiento.
     */
    // Consulta que selecciona reservas filtradas por ID de alojamiento
    @Query("SELECT r FROM ReservationEntity r WHERE r.accommodationsId = :idAccommodation")
    List<ReservationEntity> viewAccommodationReservations(@Param("idAccommodation") int idAccommodation);

    /**
     * Obtiene el historial de reservas de un usuario específico.
     * @param idUser ID del usuario.
     * @return lista de reservas del usuario.
     */
    // Consulta que selecciona reservas filtradas por ID de usuario
    @Query("SELECT r FROM ReservationEntity r WHERE r.usersId = :idUser")
    List<ReservationEntity> viewReservationHistory(@Param("idUser") int idUser);

}