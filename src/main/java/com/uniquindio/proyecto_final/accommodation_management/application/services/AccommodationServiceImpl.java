package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Qualification;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.AccommodationServiceRepository;
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
    public ResponseEntity<Accommodation> save(Accommodation accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<Accommodation>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }

    @Override
    public ResponseEntity<List<Accommodation>> ownAccommodationList(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> edit(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> delete(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> detail(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> viewMetrics(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<Accommodation>> viewAccommodationReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> acceptReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> rejectReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<Comment>> commentsList(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<Qualification>> averageGrades(int idAccommodation) {
        return null;
    }
}
