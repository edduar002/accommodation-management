package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    ResponseEntity<Accommodation> save(Accommodation accommodation);

    ResponseEntity<List<Accommodation>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin);

    ResponseEntity<List<Accommodation>> ownAccommodationList(int idHost);

    ResponseEntity<Accommodation> edit(int idAccommodation);

    ResponseEntity<Accommodation> delete(int idAccommodation);

    ResponseEntity<Accommodation> detail(int accommodation);

    ResponseEntity<Accommodation> viewMetrics(int accommodation);

    ResponseEntity<List<Accommodation>> viewAccommodationReservations(int idReservation);

    ResponseEntity<Accommodation> acceptReservationRequests(int idAccommodation);

    ResponseEntity<Accommodation> rejectReservationRequests(int idAccommodation);

    ResponseEntity<List<Comment>> commentsList(int idAccommodation);

    ResponseEntity<List<Qualification>> averageGrades(int idAccommodation);
}
