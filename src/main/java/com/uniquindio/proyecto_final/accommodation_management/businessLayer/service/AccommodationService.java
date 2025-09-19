package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    AccommodationEntity save(AccommodationEntity accommodation);

    List<AccommodationEntity> searchAvailableAccommodations();

    List<AccommodationEntity> ownAccommodationList(int idHost);

    Optional<AccommodationEntity> edit(int idAccommodation, AccommodationEntity accommodation);

    Optional<AccommodationEntity> delete(int idAccommodation);

    AccommodationEntity detail(int accommodation);

    ResponseEntity<AccommodationEntity> viewMetrics(int accommodation);

    Double averageGrades(int idAccommodation);

}
