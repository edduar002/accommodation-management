package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CommentEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository repository;

    @Override
    @Transactional
    public AccommodationEntity save(AccommodationEntity dto) {
        return repository.save(dto);
    }

    @Override
    public ResponseEntity<List<AccommodationEntity>> searchAvailableAccommodations(int ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationEntity>> ownAccommodationList(int idHost) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> edit(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> delete(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> detail(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> viewMetrics(int accommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationEntity>> viewAccommodationReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> acceptReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> rejectReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<CommentEntity>> commentsList(int idAccommodation) {
        return null;
    }

    @Override
    public ResponseEntity<List<QualificationEntity>> averageGrades(int idAccommodation) {
        return null;
    }
}
