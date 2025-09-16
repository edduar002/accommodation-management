package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.QualificationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository repository;

    @Override
    @Transactional
    public ReservationEntity save(ReservationEntity dto) {
        return repository.save(dto);
    }

    @Override
    public ResponseEntity<ReservationEntity> makeReservations(LocalDate checkIn, LocalDate checkOut) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationEntity> cancelReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationEntity>> viewReservationHistory(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationEntity> viewAccommodationDetails(int idAccommodation) {
        return null;
    }
}
