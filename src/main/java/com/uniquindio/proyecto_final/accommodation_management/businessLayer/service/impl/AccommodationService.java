package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    ResponseEntity<AccommodationEntity> save(AccommodationEntity accommodation);

    ResponseEntity<List<AccommodationEntity>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin);

    ResponseEntity<List<AccommodationEntity>> ownAccommodationList(int idHost);

    ResponseEntity<AccommodationEntity> edit(int idAccommodation);

    ResponseEntity<AccommodationEntity> delete(int idAccommodation);

    ResponseEntity<AccommodationEntity> detail(int accommodation);

    ResponseEntity<AccommodationEntity> viewMetrics(int accommodation);

    ResponseEntity<List<AccommodationEntity>> viewAccommodationReservations(int idReservation);

    ResponseEntity<AccommodationEntity> acceptReservationRequests(int idAccommodation);

    ResponseEntity<AccommodationEntity> rejectReservationRequests(int idAccommodation);

    ResponseEntity<List<CommentEntity>> commentsList(int idAccommodation);

    ResponseEntity<List<QualificationEntity>> averageGrades(int idAccommodation);
}
