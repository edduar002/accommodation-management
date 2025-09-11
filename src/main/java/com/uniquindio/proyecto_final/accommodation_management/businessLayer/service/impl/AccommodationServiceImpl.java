package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationServiceRepository repository;

    @Override
    public ResponseEntity<AccommodationDTO> save(AccommodationDTO accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationDTO>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationDTO>> ownAccommodationList(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> edit(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> delete(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> detail(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> viewMetrics(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationDTO>> viewAccommodationReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> acceptReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> rejectReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<CommentDTO>> commentsList(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<QualificationDTO>> averageGrades(int idAccommodation) {
        return null;
    }
}
