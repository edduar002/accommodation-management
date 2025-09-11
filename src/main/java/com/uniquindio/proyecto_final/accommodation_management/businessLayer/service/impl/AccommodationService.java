package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    ResponseEntity<AccommodationDTO> save(AccommodationDTO accommodation);

    ResponseEntity<List<AccommodationDTO>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin);

    ResponseEntity<List<AccommodationDTO>> ownAccommodationList(int idHost);

    ResponseEntity<AccommodationDTO> edit(int idAccommodation);

    ResponseEntity<AccommodationDTO> delete(int idAccommodation);

    ResponseEntity<AccommodationDTO> detail(int accommodation);

    ResponseEntity<AccommodationDTO> viewMetrics(int accommodation);

    ResponseEntity<List<AccommodationDTO>> viewAccommodationReservations(int idReservation);

    ResponseEntity<AccommodationDTO> acceptReservationRequests(int idAccommodation);

    ResponseEntity<AccommodationDTO> rejectReservationRequests(int idAccommodation);

    ResponseEntity<List<CommentDTO>> commentsList(int idAccommodation);

    ResponseEntity<List<QualificationDTO>> averageGrades(int idAccommodation);
}
