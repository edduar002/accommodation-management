package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    AccommodationEntity save(AccommodationEntity accommodation);

    List<AccommodationEntity> searchAvailableAccommodations();

    List<AccommodationEntity> ownAccommodationList(int idHost);

    ResponseEntity<AccommodationEntity> edit(int idAccommodation);

    ResponseEntity<AccommodationEntity> delete(int idAccommodation);

    AccommodationEntity detail(int accommodation);

    ResponseEntity<AccommodationEntity> viewMetrics(int accommodation);

    ResponseEntity<AccommodationEntity> acceptReservationRequests(int idAccommodation);

    ResponseEntity<AccommodationEntity> rejectReservationRequests(int idAccommodation);

    Double averageGrades(int idAccommodation);

}
