package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Integer> {

    @Query("SELECT a FROM AccommodationEntity a WHERE a.active = true")
    List<AccommodationEntity> searchAvailableAccommodations();

    @Query("SELECT a FROM AccommodationEntity a WHERE a.hostsId = :idHost")
    List<AccommodationEntity> ownAccommodationList(@Param("idHost") int idHost);

}
